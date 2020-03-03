/**
 *
 */
package visitor;

import ast.Add;
import ast.Assignment;
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

/**
 * @author carr
 *
 */
public class CSourceVisitor implements ASTVisitor<String> {

	/**
	 *
	 */
	public CSourceVisitor() throws CException {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Add)
	 */
	@Override
	public String visit(Add n) throws CException {

		return " + ";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Assignment)
	 */
	@Override
	public String visit(Assignment n) throws CException {
		return " = ";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CByte)
	 */
	@Override
	public String visit(CByte n) throws CException {
		return n.getTypeName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CInt)
	 */
	@Override
	public String visit(CInt n) throws CException {
		return n.getTypeName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CLong)
	 */
	@Override
	public String visit(CLong n) throws CException {
		return n.getTypeName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Constant)
	 */
	@Override
	public String visit(Constant n) throws CException {
		return n.getLabel();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.CShort)
	 */
	@Override
	public String visit(CShort n) throws CException {
		return n.getTypeName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Divide)
	 */
	@Override
	public String visit(Divide n) throws CException {
		return " / ";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.IdDecl)
	 */
	@Override
	public String visit(IdDecl n) throws CException {
		return n.getLabel();
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
		return n.getLabel();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.InitializeDecl)
	 */
	@Override
	public String visit(InitializeDecl n) throws CException {
		return n.getId() + " = " + n.getInitialValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Multiply)
	 */
	@Override
	public String visit(Multiply n) throws CException {
		return " * ";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Program)
	 */
	@Override
	public String visit(Program n) throws CException {

		// Use Java first-class functions on streams to walk the list to obtain a list
		// of strings for each statement
		// Then, reduce that list to a single string.
		String str = n.getStatements().stream().map(s -> {
			try {
				return (String) s.accept(this);
			} catch (CException e) { // this visitor does not throw CException, but this try/catch is needed for
										// proper Java semantics
				e.printStackTrace();
				System.exit(-1);
			}
			return "";
		}).reduce("", (a, b) -> a + b + ";\n");
		return str.substring(0, str.length() - 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Subtract)
	 */
	@Override
	public String visit(Subtract n) throws CException {
		return " - ";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Signed)
	 */
	@Override
	public String visit(Signed n) throws CException {
		return "signed";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.TypeCast)
	 */
	@Override
	public String visit(TypeCast n) throws CException {
		return "(" + n.getCastType().accept(this) + ")";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.TypeSpecification)
	 */
	@Override
	public String visit(TypeSpecification n) throws CException {
		return (n.isSigned() ? "" : "unsigned ") + n.getType().accept(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.UnaryMinus)
	 */
	@Override
	public String visit(UnaryMinus n) throws CException {
		return "-";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.UnaryPlus)
	 */
	@Override
	public String visit(UnaryPlus n) throws CException {
		return "+";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.Unsigned)
	 */
	@Override
	public String visit(Unsigned n) throws CException {
		return "unsigned";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see visitor.ASTVisitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public String visit(VariableDeclaration n) throws CException {
		String str = n.getTypeSpecification().accept(this) + " " + n.getDecls().stream().map(d -> {
			try {
				return (String) d.accept(this);
			} catch (CException e) {// this visitor does not throw CException, but this try/catch is needed for
									// proper Java semantics
				e.printStackTrace();
				System.exit(-1);
			}
			return "";
		}).reduce("", (a, b) -> a + b + ", ");
		return str.substring(0,str.length()-2);
	}

	@Override
	public String visit(Put n) throws CException {
		return "put ";
	}

}
