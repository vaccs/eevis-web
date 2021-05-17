/**
 *
 */
package org.vaccs.eevis.visitor;

import org.vaccs.eevis.ast.Add;
import org.vaccs.eevis.ast.Assignment;
import org.vaccs.eevis.ast.CByte;
import org.vaccs.eevis.ast.CInt;
import org.vaccs.eevis.ast.CLong;
import org.vaccs.eevis.ast.CShort;
import org.vaccs.eevis.ast.Constant;
import org.vaccs.eevis.ast.Divide;
import org.vaccs.eevis.ast.IdDecl;
import org.vaccs.eevis.ast.IdDef;
import org.vaccs.eevis.ast.IdRef;
import org.vaccs.eevis.ast.InitializeDecl;
import org.vaccs.eevis.ast.Multiply;
import org.vaccs.eevis.ast.Program;
import org.vaccs.eevis.ast.Put;
import org.vaccs.eevis.ast.Signed;
import org.vaccs.eevis.ast.Subtract;
import org.vaccs.eevis.ast.TypeCast;
import org.vaccs.eevis.ast.TypeSpecification;
import org.vaccs.eevis.ast.UnaryMinus;
import org.vaccs.eevis.ast.UnaryPlus;
import org.vaccs.eevis.ast.Unsigned;
import org.vaccs.eevis.ast.VariableDeclaration;
import org.vaccs.eevis.util.CException;

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
		return "(" + n.getCastType().accept(this) + ")" + n.getExpression().accept(this);
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
		return str.substring(0, str.length() - 2);
	}

	@Override
	public String visit(Put n) throws CException {
		return "put ";
	}

}
