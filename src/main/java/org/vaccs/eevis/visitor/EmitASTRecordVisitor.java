/**
 *
 */
package org.vaccs.eevis.visitor;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import org.vaccs.eevis.ast.ASTNode;
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
import org.vaccs.eevis.driver.CExpr;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.vaccsio.VaccsASTRecord;
import org.vaccs.eevis.vaccsio.VaccsIORecordFactory;

/**
 * @author carr
 *
 */
public class EmitASTRecordVisitor implements ASTVisitor<String> {

	private FileOutputStream fos;
	private PrintWriter fw;
	private StringWriter sw = null;
	private CSourceVisitor csv;
	private VaccsIORecordFactory viorFactory = new VaccsIORecordFactory();
	private String separator = ",";

	/**
	 *
	 */
	public EmitASTRecordVisitor() {
		try {
			csv = new CSourceVisitor();
		} catch (CException e) {
			System.err.println("Error emitting AST: " + e);
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public EmitASTRecordVisitor addFOS(FileOutputStream fos) {
		this.fos = fos;
		return this;
	}

	public EmitASTRecordVisitor addFW(PrintWriter fw) {
		this.fw = fw;
		return this;
	}

	public EmitASTRecordVisitor addSW(StringWriter sw) {
		this.sw = sw;
		return this;
	}

	/**
	 * Build a vaccsio AST record for the visualization. Note that some of the
	 * subtrees in the AST related to variable declarations are compressed into a
	 * single node in the emitted AST. This is to improve readability in the
	 * visualization system.
	 *
	 * @param n        an AST node
	 * @param children the children of the AST node that should be passed to the
	 *                 visualization
	 * @throws CException
	 */
	public void buildASTRecord(ASTNode n, List<ASTNode> children) throws CException {
		VaccsASTRecord var = ((VaccsASTRecord) viorFactory.makeVaccIORecord("VaccsASTRecord")).addId(n.getNodeId())
				.addParentId(n.getParent().getNodeId()).addSourceLine((short) n.getLineNumber())
				.addSource((String) n.accept(csv));

		if (children != null)
			for (ASTNode c : children)
				var.addChild(c.getNodeId());

		for (Short s : n.getIlocLines())
			var.addIlocLine(s);

		try {
			if (sw != null)
				var.write(sw);
			else if (CExpr.dumpAscii)
				var.write(fw);
			else
				var.write(fos);
		} catch (IOException e) {
			System.err.println("IO Error creating AST Node: " + e);
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public void buildASTRecord(ASTNode n) throws CException {
		buildASTRecord(n, n.getChildren());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Add)
	 */
	@Override
	public String visit(Add n) throws CException {

		buildASTRecord(n);

		return n.getLeftOperand().accept(this) + separator + n.getRightOperand().accept(this) + separator
				+ n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Assignment)
	 */
	@Override
	public String visit(Assignment n) throws CException {

		buildASTRecord(n);

		return n.getRhs().accept(this) + separator + n.getLhs().accept(this) + separator + n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CByte)
	 */
	@Override
	public String visit(CByte n) throws CException {

		buildASTRecord(n);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CInt)
	 */
	@Override
	public String visit(CInt n) throws CException {
		buildASTRecord(n);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CLong)
	 */
	@Override
	public String visit(CLong n) throws CException {
		buildASTRecord(n);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Constant)
	 */
	@Override
	public String visit(Constant n) throws CException {

		buildASTRecord(n);

		return n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CShort)
	 */
	@Override
	public String visit(CShort n) throws CException {
		buildASTRecord(n);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Divide)
	 */
	@Override
	public String visit(Divide n) throws CException {

		buildASTRecord(n);

		return n.getLeftOperand().accept(this) + separator + n.getRightOperand().accept(this) + separator
				+ n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdDecl)
	 */
	@Override
	public String visit(IdDecl n) throws CException {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdDef)
	 */
	@Override
	public String visit(IdDef n) throws CException {

		buildASTRecord(n);

		return n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdRef)
	 */
	@Override
	public String visit(IdRef n) throws CException {

		buildASTRecord(n);

		return n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.InitializeDecl)
	 */
	@Override
	public String visit(InitializeDecl n) throws CException {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Multiply)
	 */
	@Override
	public String visit(Multiply n) throws CException {

		buildASTRecord(n);

		return n.getLeftOperand().accept(this) + separator + n.getRightOperand().accept(this) + separator
				+ n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Program)
	 */
	@Override
	public String visit(Program n) throws CException {

		String str = "";
		for (ASTNode c : n.getChildren()) {
			String stmtOrder = (String) c.accept(this);
			str += (stmtOrder == "" ? "" : (stmtOrder + separator));
		}

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Subtract)
	 */
	@Override
	public String visit(Subtract n) throws CException {

		buildASTRecord(n);

		return n.getLeftOperand().accept(this) + separator + n.getRightOperand().accept(this) + separator
				+ n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Signed)
	 */
	@Override
	public String visit(Signed n) throws CException {
		buildASTRecord(n);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.TypeCast)
	 */
	@Override
	public String visit(TypeCast n) throws CException {

		buildASTRecord(n);

		return n.getExpression().accept(this) + separator + n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.TypeSpecification)
	 */
	@Override
	public String visit(TypeSpecification n) throws CException {

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.UnaryMinus)
	 */
	@Override
	public String visit(UnaryMinus n) throws CException {

		buildASTRecord(n);

		return n.getOperand().accept(this) + separator + n.getNodeIdString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.UnaryPlus)
	 */
	@Override
	public String visit(UnaryPlus n) throws CException {

		buildASTRecord(n);

		return n.getOperand().accept(this) + separator + n.getNodeIdString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Unsigned)
	 */
	@Override
	public String visit(Unsigned n) throws CException {
		buildASTRecord(n);

		return "";
	}

	private List<ASTNode> buildInitializeDeclsList(List<ASTNode> cl) {
		List<ASTNode> idl = new ArrayList<ASTNode>();
		for (ASTNode c : cl)
			if (c instanceof InitializeDecl)
				idl.add(c);

		return idl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public String visit(VariableDeclaration n) throws CException {

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Put)
	 */
	@Override
	public String visit(Put n) throws CException {

		buildASTRecord(n);

		return n.getId().accept(this) + separator + n.getNodeIdString();

	}

}
