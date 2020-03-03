/**
 *
 */
package visitor;

import java.math.BigInteger;
import java.util.HashMap;

import ast.ASTNode;
import ast.Add;
import ast.Assignment;
import ast.BinaryExpression;
import ast.CByte;
import ast.CDeclaration;
import ast.CInt;
import ast.CLong;
import ast.CShort;
import ast.Constant;
import ast.Divide;
import ast.IdDecl;
import ast.IdDef;
import ast.IdRef;
import ast.InitializeDecl;
import ast.Multiply;
import ast.Program;
import ast.Put;
import ast.Signed;
import ast.Statement;
import ast.Subtract;
import ast.TypeCast;
import ast.TypeSpecification;
import ast.UnaryMinus;
import ast.UnaryPlus;
import ast.Unsigned;
import ast.VariableDeclaration;
import util.ArchitectureType;
import util.CException;
import util.CTypeTables;
import util.IA32Architecture;
import util.X86_64Architecture;

/**
 * @author carr
 *
 */
public class TypeVisitor implements ASTVisitor<Integer> {

	HashMap<String, Integer> typeTable = new HashMap<String, Integer>(); // map from variable names to declared types
	ArchitectureType arch; // the target architecture for the type checking, this determines the types for constant values in this phase

	/**
	 * Create a new TypeVisitor object to do type checking for a particular architecture
	 * @param archType the target architecture
	 * @throws CException if there a type error
	 */
	public TypeVisitor(int archType) throws CException {
		if (archType == ArchitectureType.IA32)
			arch = new IA32Architecture();
		else
			arch = new X86_64Architecture();
	}

	private void promoteOperands(BinaryExpression n, int leftType, int rightType) {
		n.getLeftOperand().setPromotedType(CTypeTables.getPromotedType(leftType));
		n.getRightOperand().setPromotedType(CTypeTables.getPromotedType(rightType));
	}

	private void convertOperands(BinaryExpression n, int leftType, int rightType) {
		int cType = CTypeTables.getConvertedType(arch,leftType,rightType);
		short rule = CTypeTables.getConversionRule(arch,leftType,rightType);
		n.getLeftOperand().setConvertedType(cType,rule);
		n.getRightOperand().setConvertedType(cType,rule);
	}

	private int typeCheckExpression(BinaryExpression n) throws CException {
		int leftType = (int) n.getLeftOperand().accept(this);
		int rightType = (int) n.getRightOperand().accept(this);

		promoteOperands(n,leftType,rightType);

		convertOperands(n,n.getLeftOperand().getPromotedType(),
				n.getRightOperand().getPromotedType());

		n.setRealType(CTypeTables.getResultingType(n.getLeftOperand().getConvertedType(),
				n.getRightOperand().getConvertedType()));

		return n.getRealType();

	}
	/*
	 * (non-Javadoc)
	 *
	 * Perform type checking on the left and right operand of an Add expression. The fields convertedType and the realType will be the same if no type
	 * coercion needs to be done. If type coercion needs to be done because of C semantics, convertedType should hold the type after coercion.
	 * The type coercion table is in CTypeTables.expressionTable
	 * @see visitor.Visitor#visit(ast.Add)
	 */
	@Override
	public Integer visit(Add n) throws CException {
		return typeCheckExpression(n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Perform type checking on the expression on the right hand side and then the variable on the left hand side
	 * The converted type of the rhs is determined by the variable type on the left-hand side of the assignment
	 * @see visitor.Visitor#visit(ast.Assignment)
	 */
	@Override
	public Integer visit(Assignment n) throws CException {
		int lhsType = (int) n.getLhs().accept(this);
		int rhsType = (int) n.getRhs().accept(this);
		n.getLhs().setPromotedType(lhsType);
		n.getRhs().setPromotedType(rhsType);

		n.getLhs().setConvertedType(lhsType);
		if (lhsType != rhsType)
			n.getRhs().setConvertedType(lhsType);
		else
			n.getRhs().setConvertedType(rhsType);

		n.setRealType(CTypeTables.NOTYPE);
		n.setPromotedType(CTypeTables.NOTYPE);

		return n.getRealType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.CByte)
	 */
	@Override
	public Integer visit(CByte n) throws CException {
		return CTypeTables.CBYTE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.CInt)
	 */
	@Override
	public Integer visit(CInt n) throws CException {
		return CTypeTables.CINT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.CLong)
	 */
	@Override
	public Integer visit(CLong n) throws CException {
		return CTypeTables.CLONG;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Determine whether the integer constant requires a long or an int for storage. NOTYPE is returned if the value does not
	 * fit in the range for a long.
	 * @see visitor.Visitor#visit(ast.Constant)
	 */
	@Override
	public Integer visit(Constant n) throws CException {
		BigInteger value = new BigInteger(n.getLabel());

		if (value.compareTo(arch.getMaxByteValue()) < 1 && value.compareTo(arch.getMinByteValue()) > -1)
			n.setRealType(CTypeTables.CBYTE);
		else if (value.compareTo(arch.getMaxShortValue()) < 1 && value.compareTo(arch.getMinShortValue()) > -1)
			n.setRealType(CTypeTables.CSHORT);
		else if (value.compareTo(arch.getMaxIntValue()) < 1 && value.compareTo(arch.getMinIntValue()) > -1)
			n.setRealType(CTypeTables.CINT);
		else if (value.compareTo(arch.getMaxLongValue()) < 1 && value.compareTo(arch.getMinLongValue()) > -1)
			n.setRealType(CTypeTables.CLONG);
		else
			n.setRealType(CTypeTables.NOTYPE);

		return n.getRealType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.CShort)
	 */
	@Override
	public Integer visit(CShort n) throws CException {
		return CTypeTables.CSHORT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Perform type checking on the left and right operand of a Divide expression. The fields convertedType and the realType will be the same if no type
	 * coercion needs to be done. If type coercion needs to be done because of C semantics, convertedType should hold the type after coercion.
	 * The type coercion table is in CTypeTables.expressionTable
	 * @see visitor.Visitor#visit(ast.Divide)
	 */
	@Override
	public Integer visit(Divide n) throws CException {
		return typeCheckExpression(n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * There is no type checking to do on the node. The variable declaration has the type information and that is where
	 * this id should get added to to typeTable.
	 * @see visitor.Visitor#visit(ast.IdDecl)
	 */
	@Override
	public Integer visit(IdDecl n) throws CException {
		return CTypeTables.NOTYPE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Type check the left-hand-side of an assignment statement
	 * @see visitor.Visitor#visit(ast.IdDef)
	 */
	@Override
	public Integer visit(IdDef n) throws CException {
		if (typeTable.containsKey(n.getId())) {
			int type = typeTable.get(n.getId());

			n.setRealType(type);
			n.setConvertedType(type);

			return type;
		} else
			throw new CException("Undeclared variable: "+n.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * Type check a variable reference
	 * @see visitor.Visitor#visit(ast.IdRef)
	 */
	@Override
	public Integer visit(IdRef n) throws CException {
		if (typeTable.containsKey(n.getId())) {
			int type = typeTable.get(n.getId());

			n.setRealType(type);

			return type;
		} else
			throw new CException("Undeclared Variable: "+n.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * Type check a declaration with an initial value
	 * @see visitor.Visitor#visit(ast.InitializeDecl)
	 */
	@Override
	public Integer visit(InitializeDecl n) throws CException {
		int type = (int) n.getInitialValueNode().accept(this);

		n.setRealType(type);

		return type;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Perform type checking on the left and right operand of a Multiply expression. The fields convertedType and the realType will be the same if no type
	 * coercion needs to be done. If type coercion needs to be done because of C semantics, convertedType should hold the type after coercion.
	 * The type coercion table is in CTypeTables.expressionTable
	 * @see visitor.Visitor#visit(ast.Multiply)
	 */
	@Override
	public Integer visit(Multiply n) throws CException {
		return typeCheckExpression(n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Walk the statements in this program and perform type checking on each statement using the visitor pattern
	 *
	 * @see visitor.Visitor#visit(ast.Program)
	 */
	@Override
	public Integer visit(Program n) throws CException {

		for (Statement statement : n.getStatements()) {
			statement.accept(this);
		}

		n.setRealType(CTypeTables.NOTYPE);

		return n.getRealType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * Perform type checking on the left and right operand of a Subtract expression. The fields convertedType and the realType will be the same if no type
	 * coercion needs to be done. If type coercion needs to be done because of C semantics, convertedType should hold the type after coercion.
	 * The type coercion table is in CTypeTables.expressionTable
	 * @see visitor.Visitor#visit(ast.Subtract)
	 */
	@Override
	public Integer visit(Subtract n) throws CException {
		return typeCheckExpression(n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * This method should not be called since this should be processed in variable declaration or type conversion expression
	 * @see visitor.Visitor#visit(ast.Signed)
	 */
	@Override
	public Integer visit(Signed n) throws CException {
		return CTypeTables.NOTYPE;
	}

	/*
	 * (non-Javadoc)
	 * Type check a type cast (return the  type)
	 * @see visitor.Visitor#visit(ast.TypeCast)
	 */
	@Override
	public Integer visit(TypeCast n) throws CException {
		int castType = (int) n.getCastType().accept(this);

		n.getExpression().accept(this);

		n.setRealType(castType);
		n.getExpression().setConvertedType(castType);

		return n.getRealType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.TypeSpecification)
	 */
	@Override
	public Integer visit(TypeSpecification n) throws CException {
		int type = (int) n.getType().accept(this);

		if (n.isUnsigned())
			type = CTypeTables.convertToUnsignedType(type);

		n.setRealType(type);

		return type;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.UnaryMinus)
	 */
	@Override
	public Integer visit(UnaryMinus n) throws CException {

		int type = (int) n.getOperand().accept(this);

		n.setRealType(CTypeTables.convertToSignedType(type));
		n.getOperand().setConvertedType(n.getRealType());

		return n.getRealType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.Visitor#visit(ast.UnaryPlus)
	 */
	@Override
	public Integer visit(UnaryPlus n) throws CException {

		int type = (int)n.getOperand().accept(this);

		n.setRealType(type);
		n.getOperand().setConvertedType(type);

		return type;
	}

	/*
	 * (non-Javadoc)
	 *
	 * This method should not be called since this should be processed in variable declaration or type conversion expression
	 * @see visitor.Visitor#visit(ast.Unsigned)
	 */
	@Override
	public Integer visit(Unsigned n) throws CException {
		return CTypeTables.NOTYPE;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Determine the type from the type specification, then walk the list of variables defined and add the type of the
	 * variables to typeTable
	 * @see visitor.Visitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public Integer visit(VariableDeclaration n) throws CException {
		Integer declaredType = (Integer) n.getTypeSpecification().accept(this);

		for (ASTNode d : n.getDecls()) {
			String id = ((CDeclaration)d).getId();
			typeTable.put(id,declaredType);

			if (d instanceof InitializeDecl)
				d.setRealType((int)((InitializeDecl)d).accept(this));
			else
				d.setRealType(declaredType);

			d.setConvertedType(declaredType);

		}

		n.setRealType(CTypeTables.NOTYPE);
		n.setConvertedType(CTypeTables.NOTYPE);

		return n.getRealType();
	}

	@Override
	public Integer visit(Put n) throws CException {
		Integer type = (Integer) n.getId().accept(this);
		n.getId().setPromotedType(type);
		n.getId().setConvertedType(type);
		n.setRealType(CTypeTables.NOTYPE);
		n.setPromotedType(CTypeTables.NOTYPE);

		return type;
	}

}
