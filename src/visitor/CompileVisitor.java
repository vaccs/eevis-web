/**
 *
 */
package visitor;

import java.math.BigInteger;

import ast.ASTNode;
import ast.Add;
import ast.Assignment;
import ast.CByte;
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
import iloc.IlocInstruction;
import iloc.InstructionFactory;
import iloc.AddInstruction;
import iloc.SubtractInstruction;
import iloc.DivideInstruction;
import iloc.MultiplyInstruction;
import iloc.operand.Operand;
import iloc.operand.OperandFactory;
import util.ArchitectureType;
import util.CException;
import util.CTypeTables;
import util.IlocProgram;
import value.ByteValue;
import value.CValue;
import value.IntValue;
import value.LongValue;
import value.ShortValue;
import value.CValueFactory;

/**
 * @author carr
 *
 */
public class CompileVisitor implements ASTVisitor<Operand> {

	IlocProgram program;
	OperandFactory opFactory = new OperandFactory();
	InstructionFactory instFactory = new InstructionFactory();
	CValueFactory cValFactory = new CValueFactory();
	private ArchitectureType archType;


	/**
	 *
	 */
	public CompileVisitor() throws CException {
		program = new IlocProgram();
	}

	public CompileVisitor addArchitecture(ArchitectureType arch) throws CException {
		archType = arch;
		return this;
	}



	public IlocProgram getProgram() {
		return program;
	}

	private int tempNum = 0;

	private String genTempName() {
		return "%t" + Integer.toString(tempNum++);
	}

	private int typeBits(int type) {
		int tBits = 0;
		switch (type) {
		case CTypeTables.CBYTE:
		case CTypeTables.CUBYTE:
			tBits = archType.byteSize()*8;
			break;
		case CTypeTables.CSHORT:
		case CTypeTables.CUSHORT:
			tBits = archType.shortSize()*8;
			break;
		case CTypeTables.CINT:
		case CTypeTables.CUINT:
			tBits = archType.intSize()*8;
			break;
		default:
			tBits = archType.longSize()*8;
		}

		return tBits;
	}

	private String getOperandTypeString(int type) {
		switch (type) {
		case CTypeTables.CBYTE:
			return "ByteOperand";
		case CTypeTables.CUBYTE:
			return "UnsignedByteOperand";
		case CTypeTables.CSHORT:
			return "ShortOperand";
		case CTypeTables.CUSHORT:
			return "UnsignedShortOperand";
		case CTypeTables.CINT:
			return "IntOperand";
		case CTypeTables.CUINT:
			return "UnsignedIntOperand";
		case CTypeTables.CLONG:
			return "LongOperand";
		case CTypeTables.CULONG:
			return "UnsignedLongOperand";
		default:
			return null;
		}
	}

	private String getCopyPrefix(int srcType) {
		switch (srcType) {
		case CTypeTables.CBYTE:
			return "B2";
		case CTypeTables.CUBYTE:
			return "Ub2";
		case CTypeTables.CSHORT:
			return "S2";
		case CTypeTables.CUSHORT:
			return "Us2";
		case CTypeTables.CINT:
			return "I2";
		case CTypeTables.CUINT:
			return "Ui2";
		case CTypeTables.CLONG:
			return "L2";
		case CTypeTables.CULONG:
			return "Ul2";
		default:
			return null;
		}
	}

	private String getCopySuffix(int destType) {
		switch (destType) {
		case CTypeTables.CBYTE:
			return "bInstruction";
		case CTypeTables.CUBYTE:
			return "ubInstruction";
		case CTypeTables.CSHORT:
			return "sInstruction";
		case CTypeTables.CUSHORT:
			return "usInstruction";
		case CTypeTables.CINT:
			return "iInstruction";
		case CTypeTables.CUINT:
			return "uiInstruction";
		case CTypeTables.CLONG:
			return "lInstruction";
		case CTypeTables.CULONG:
			return "ulInstruction";
		default:
			return null;
		}
	}

	private String getCopyInstString(int destType,int srcType) {
		return getCopyPrefix(srcType) + getCopySuffix(destType);
	}

	private Operand doPromotion(ASTNode n, Operand op) throws CException {
		if (n.getPromotedType() == n.getRealType())
			return op;
		else {
			Operand result =
					opFactory.makeOperand(getOperandTypeString(n.getPromotedType()))
						.addName(genTempName());
			IlocInstruction copy =
					instFactory.makeInstruction(getCopyInstString(n.getPromotedType(),n.getRealType()))
						.addSrc1(op)
						.addDest(result)
						.addASTNode(n)
						.addPromotion();
			program.add(copy);
			n.addIlocLine(copy.getIlocId());

			return result;
		}
	}

	private Operand doConversion(ASTNode n, Operand op) throws CException {
		if (n.getConvertedType() == n.getPromotedType())
			return op;
		else {
			Operand result =
					opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
						.addName(genTempName());
			IlocInstruction copy =
					instFactory.makeInstruction(getCopyInstString(n.getConvertedType(),
																  n.getPromotedType()))
						.addSrc1(op)
						.addDest(result)
						.addASTNode(n)
						.addConversionRule(n.getRule());
			program.add(copy);
			n.addIlocLine(copy.getIlocId());

			return result;
		}
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Add)
	 */
	@Override
	public Operand visit(Add n) throws CException {
		Operand leftOper = (Operand) n.getLeftOperand().accept(this);
		Operand rightOper = (Operand) n.getRightOperand().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());

		IlocInstruction inst =
				((AddInstruction)instFactory.makeInstruction("AddInstruction"))
					.addSrc1(leftOper.copy())
					.addSrc2(rightOper.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Assignment)
	 */
	@Override
	public Operand visit(Assignment n) throws CException {
		Operand op = (Operand) n.getRhs().accept(this);
		Operand result = (Operand) n.getLhs().accept(this);
		IlocInstruction copy =
				instFactory.makeInstruction(getCopyInstString(n.getLhs().getConvertedType(),
															  n.getRhs().getConvertedType()))
					.addSrc1(op.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(copy);
		n.addIlocLine(copy.getIlocId());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.CByte)
	 */
	@Override
	public Operand visit(CByte n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.CInt)
	 */
	@Override
	public Operand visit(CInt n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.CLong)
	 */
	@Override
	public Operand visit(CLong n) throws CException {
		return null;
	}

	private CValue makeCValueForConstant(String value, int realType) throws CException {
		CValue cVal = null;
		try {
			switch (realType) {
			case CTypeTables.CBYTE:
				cVal = ((ByteValue)cValFactory.makeCValue("ByteValue")).addValue(Byte.parseByte(value));
				break;
			case CTypeTables.CINT:
				cVal = ((IntValue)cValFactory.makeCValue("IntValue")).addValue(Integer.parseInt(value));
				break;
			case CTypeTables.CLONG:
				cVal = ((LongValue)cValFactory.makeCValue("LongValue")).addValue(Long.parseLong(value));
				break;
			case CTypeTables.CSHORT:
				cVal = ((ShortValue)cValFactory.makeCValue("ShortValue")).addValue(Short.parseShort(value));
				break;
			default:
				throw new RuntimeException("A constant was given an unsigned real type. This should not be possible.");
			}
		} catch (NumberFormatException e) {
			System.err.println(e);
			System.exit(-1);
		}
		return cVal;
	}

	private String convertConstant(String value,int realType, int convertedType) throws CException {
		CValue cVal = makeCValueForConstant(value,realType);
		String valString = null;
		switch (convertedType) {
		case CTypeTables.CBYTE:
			valString = Byte.toString(cVal.getByteValue());
			break;
		case CTypeTables.CINT:
			valString = Integer.toString(cVal.getIntValue());
			break;
		case CTypeTables.CLONG:
			valString = Long.toString(cVal.getLongValue());
			break;
		case CTypeTables.CSHORT:
			valString = Short.toString(cVal.getShortValue());
			break;
		case CTypeTables.CUBYTE:
			valString = Integer.toUnsignedString(cVal.getUnsignedByteValue());
			break;
		case CTypeTables.CUINT:
			valString = Long.toUnsignedString(cVal.getUnsignedIntValue());
			break;
		case CTypeTables.CULONG:
			valString = Long.toUnsignedString(cVal.getUnsignedLongValue());
			break;
		case CTypeTables.CUSHORT:
			valString = Integer.toUnsignedString(cVal.getUnsignedShortValue());
			break;
		}

		return valString;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Constant)
	 */
	@Override
	public Operand visit(Constant n) throws CException {

		Operand op = opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
				.addName(convertConstant(n.getLabel(),n.getRealType(),n.getConvertedType()))
				.addIsConstant();

		Operand result = opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
				.addName(genTempName());

		IlocInstruction inst = instFactory.makeInstruction("LoadIInstruction")
				.addSrc1(op)
				.addDest(result)
				.addASTNode(n);

		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.CShort)
	 */
	@Override
	public Operand visit(CShort n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Divide)
	 */
	@Override
	public Operand visit(Divide n) throws CException {
		Operand leftOper = (Operand) n.getLeftOperand().accept(this);
		Operand rightOper = (Operand) n.getRightOperand().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());

		IlocInstruction inst =
				((DivideInstruction)instFactory.makeInstruction("DivideInstruction"))
					.addSrc1(leftOper.copy())
					.addSrc2(rightOper.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.IdDecl)
	 */
	@Override
	public Operand visit(IdDecl n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.IdDef)
	 */
	@Override
	public Operand visit(IdDef n) throws CException {
		return opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
				.addName(n.getId());
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.IdRef)
	 */
	@Override
	public Operand visit(IdRef n) throws CException {
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(n.getId());

		result = doPromotion(n,result);
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.InitializeDecl)
	 */
	@Override
	public Operand visit(InitializeDecl n) throws CException {
		Operand op = opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
				.addName(convertConstant(n.getInitialValue(),n.getRealType(),n.getConvertedType()))
				.addIsConstant();

		Operand result = opFactory.makeOperand(getOperandTypeString(n.getConvertedType()))
				.addName(n.getId());

		IlocInstruction inst = instFactory.makeInstruction("LoadIInstruction")
				.addSrc1(op)
				.addDest(result)
				.addASTNode(n)
				.addInitialization();

		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Multiply)
	 */
	@Override
	public Operand visit(Multiply n) throws CException {
		Operand leftOper = (Operand) n.getLeftOperand().accept(this);
		Operand rightOper = (Operand) n.getRightOperand().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());

		IlocInstruction inst =
				((MultiplyInstruction)instFactory.makeInstruction("MultiplyInstruction"))
					.addSrc1(leftOper.copy())
					.addSrc2(rightOper.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Program)
	 */
	@Override
	public Operand visit(Program n) throws CException {
		for (Statement statement : n.getStatements()) {
			statement.accept(this);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Subtract)
	 */
	@Override
	public Operand visit(Subtract n) throws CException {
		Operand leftOper = (Operand) n.getLeftOperand().accept(this);
		Operand rightOper = (Operand) n.getRightOperand().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());

		IlocInstruction inst =
				((SubtractInstruction)instFactory.makeInstruction("SubtractInstruction"))
					.addSrc1(leftOper.copy())
					.addSrc2(rightOper.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Signed)
	 */
	@Override
	public Operand visit(Signed n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.TypeCast)
	 */
	@Override
	public Operand visit(TypeCast n) throws CException {
		Operand op = (Operand) n.getExpression().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());
		IlocInstruction copy =
				instFactory.makeInstruction(getCopyInstString(n.getExpression().getConvertedType(),
															  n.getRealType()))
					.addSrc1(op.copy())
					.addDest(result)
					.addASTNode(n);
		program.add(copy);
		n.addIlocLine(copy.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.TypeSpecification)
	 */
	@Override
	public Operand visit(TypeSpecification n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.UnaryMinus)
	 */
	@Override
	public Operand visit(UnaryMinus n) throws CException {
		Operand op = opFactory.makeOperand(getOperandTypeString(n.getOperand().getConvertedType()))
				.addName(convertConstant("0",n.getOperand().getConvertedType(),n.getOperand().getConvertedType()))
				.addIsConstant();

		Operand leftOper = opFactory.makeOperand(getOperandTypeString(n.getOperand().getConvertedType()))
				.addName(genTempName());

		IlocInstruction inst = instFactory.makeInstruction("LoadIInstruction")
				.addSrc1(op)
				.addDest(leftOper)
				.addASTNode(n);

		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		Operand rightOper = (Operand) n.getOperand().accept(this);
		Operand result = opFactory.makeOperand(getOperandTypeString(n.getRealType()))
				.addName(genTempName());

		inst = ((SubtractInstruction)instFactory.makeInstruction("SubtractInstruction"))
					.addSrc1(leftOper.copy())
					.addSrc2(rightOper)
					.addDest(result)
					.addASTNode(n);
		program.add(inst);
		n.addIlocLine(inst.getIlocId());

		result = doPromotion(n,result.copy());
		result = doConversion(n,result.copy());

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.UnaryPlus)
	 */
	@Override
	public Operand visit(UnaryPlus n) throws CException {
		Operand result = (Operand) n.getOperand().accept(this);

		result = doConversion(n,result);

		return result;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.Unsigned)
	 */
	@Override
	public Operand visit(Unsigned n) throws CException {
		return null;
	}

	/* (non-Javadoc)
	 * @see visitor.Visitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public Operand visit(VariableDeclaration n) throws CException {
		for (ASTNode d : n.getDecls()) {
			d.accept(this);
		}

		return null;
	}

	@Override
	public Operand visit(Put n) throws CException {
		Operand op = (Operand) n.getId().accept(this);
		IlocInstruction out =
				instFactory.makeInstruction("OutputInstruction")
					.addSrc1(op)
					.addASTNode(n);
		program.add(out);
		n.addIlocLine(out.getIlocId());
		return op;
	}

}
