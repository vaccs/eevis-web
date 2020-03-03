/** *
 */
package visitor;

import ast.Add;
import ast.Assignment;
import ast.ASTNode;
import ast.CByte;
import ast.CInt;
import ast.CLong;
import ast.CShort;
import ast.CTypeName;
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
import util.CException;

import java.util.List;

/**
 * @author carr
 *
 */
public class EquationSourceVisitor implements ASTVisitor<String> {

	/**
	 *
	 */
	public EquationSourceVisitor() throws CException {
	}

	private String wrapParens(ASTNode n,String str) {
		return n.getParens() ? "(" + str + ")" : str;

	}
	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Add)
	 */
	@Override
	public String visit(Add n) throws CException {

		return wrapParens(n,n.getLeftOperand().accept(this) + " + " + n.getRightOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Assignment)
	 */
	@Override
	public String visit(Assignment n) throws CException {
		return wrapParens(n,n.getLhs().accept(this) + " = " + n.getRhs().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CByte)
	 */
	@Override
	public String visit(CByte n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CInt)
	 */
	@Override
	public String visit(CInt n) throws CException {

		return null;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CLong)
	 */
	@Override
	public String visit(CLong n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Constant)
	 */
	@Override
	public String visit(Constant n) throws CException {
		return wrapParens(n,n.getLabel());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CShort)
	 */
	@Override
	public String visit(CShort n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Divide)
	 */
	@Override
	public String visit(Divide n) throws CException {
		return wrapParens(n,n.getLeftOperand().accept(this) + " / " + n.getRightOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.IdDecl)
	 */
	@Override
	public String visit(IdDecl n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.IdDef)
	 */
	@Override
	public String visit(IdDef n) throws CException {
		return n.getLabel();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.IdRef)
	 */
	@Override
	public String visit(IdRef n) throws CException {
		return wrapParens(n,n.getLabel());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.InitializeDecl)
	 */
	@Override
	public String visit(InitializeDecl n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Multiply)
	 */
	@Override
	public String visit(Multiply n) throws CException {
		return wrapParens(n,n.getLeftOperand().accept(this) + " * " + n.getRightOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Program)
	 */
	@Override
	public String visit(Program n) throws CException {

		List<Statement> sl = n.getStatements();
		return (String) sl.get(sl.size()-1).accept(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Subtract)
	 */
	@Override
	public String visit(Subtract n) throws CException {
		return wrapParens(n,n.getLeftOperand().accept(this) + " - " + n.getRightOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Signed)
	 */
	@Override
	public String visit(Signed n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.TypeCast)
	 */
	@Override
	public String visit(TypeCast n) throws CException {
		return wrapParens(n,"(" + n.getCastType().accept(this) + ")");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.TypeSpecification)
	 */
	@Override
	public String visit(TypeSpecification n) throws CException {
		return (n.isSigned() ? "" : "unsigned ") + n.getType().getTypeName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.UnaryMinus)
	 */
	@Override
	public String visit(UnaryMinus n) throws CException {
		return wrapParens(n,"-" + n.getOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.UnaryPlus)
	 */
	@Override
	public String visit(UnaryPlus n) throws CException {
		return wrapParens(n,"+" + n.getOperand().accept(this));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Unsigned)
	 */
	@Override
	public String visit(Unsigned n) throws CException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public String visit(VariableDeclaration n) throws CException {
		return null;
	}

	@Override
	public String visit(Put n) throws CException {
		return null;
	}
}
