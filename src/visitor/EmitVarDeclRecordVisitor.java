/**
 *
 */
package visitor;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

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
import ast.Subtract;
import ast.TypeCast;
import ast.TypeSpecification;
import ast.UnaryMinus;
import ast.UnaryPlus;
import ast.Unsigned;
import ast.VariableDeclaration;
import driver.CExpr;
import util.CException;
import util.CTypeTables;
import value.*;
import vaccsio.VaccsIORecordFactory;
import vaccsio.VaccsVarDeclRecord;

/**
 * @author carr
 *
 */
public class EmitVarDeclRecordVisitor implements ASTVisitor<Void> {

	private FileOutputStream fos;
	private PrintWriter fw;
	private StringWriter sw;
	private CSourceVisitor csv;
	private VaccsIORecordFactory viorFactory = new VaccsIORecordFactory();
	private TypeSpecification current_ts = null;
	private CValueFactory cValFactory = new CValueFactory();

	/**
	 *
	 */
	public EmitVarDeclRecordVisitor() {
	}

	public EmitVarDeclRecordVisitor addFOS(FileOutputStream fos) {
		this.fos = fos;
		return this;
	}

	public EmitVarDeclRecordVisitor addFW(PrintWriter fw) {
		this.fw = fw;
		return this;
	}

	public EmitVarDeclRecordVisitor addSW(StringWriter sw) {
		this.sw = sw;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Add)
	 */
	@Override
	public Void visit(Add n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Assignment)
	 */
	@Override
	public Void visit(Assignment n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CByte)
	 */
	@Override
	public Void visit(CByte n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CInt)
	 */
	@Override
	public Void visit(CInt n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CLong)
	 */
	@Override
	public Void visit(CLong n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Constant)
	 */
	@Override
	public Void visit(Constant n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.CShort)
	 */
	@Override
	public Void visit(CShort n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Divide)
	 */
	@Override
	public Void visit(Divide n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdDecl)
	 */
	@Override
	public Void visit(IdDecl n) throws CException {
		VaccsVarDeclRecord var = ((VaccsVarDeclRecord) viorFactory.makeVaccIORecord("VaccsVarDeclRecord"))
				.addName(n.getId())
				.addType((current_ts.isSigned() ? "signed " : "unsigned ") + current_ts.getType().getTypeName())
				.addValue("<NOVALUE>");

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

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdDef)
	 */
	@Override
	public Void visit(IdDef n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.IdRef)
	 */
	@Override
	public Void visit(IdRef n) throws CException {

		return null;
	}

	private CValue makeCValueForConstant(String value, int realType) throws CException {
		CValue cVal = null;
		try {
			switch (realType) {
				case CTypeTables.CBYTE:
					cVal = ((ByteValue) cValFactory.makeCValue("ByteValue")).addValue(Byte.parseByte(value));
					break;
				case CTypeTables.CINT:
					cVal = ((IntValue) cValFactory.makeCValue("IntValue")).addValue(Integer.parseInt(value));
					break;
				case CTypeTables.CLONG:
					cVal = ((LongValue) cValFactory.makeCValue("LongValue")).addValue(Long.parseLong(value));
					break;
				case CTypeTables.CSHORT:
					cVal = ((ShortValue) cValFactory.makeCValue("ShortValue")).addValue(Short.parseShort(value));
					break;
				default:
					throw new RuntimeException(
							"A constant was given an unsigned real type. This should not be possible.");
			}
		} catch (NumberFormatException e) {
			System.err.println(e);
			System.exit(-1);
		}
		return cVal;
	}

	private String convertConstant(String value, int realType, int convertedType) throws CException {
		CValue cVal = makeCValueForConstant(value, realType);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.InitializeDecl)
	 */
	@Override
	public Void visit(InitializeDecl n) throws CException {

		VaccsVarDeclRecord var = ((VaccsVarDeclRecord) viorFactory.makeVaccIORecord("VaccsVarDeclRecord"))
				.addName(n.getId())
				.addType((current_ts.isSigned() ? "signed " : "unsigned ") + current_ts.getType().getTypeName())
				.addValue(convertConstant(n.getInitialValue(), n.getRealType(), n.getConvertedType()));

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

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Multiply)
	 */
	@Override
	public Void visit(Multiply n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Program)
	 */
	@Override
	public Void visit(Program n) throws CException {

		for (ASTNode c : n.getChildren())
			c.accept(this);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Subtract)
	 */
	@Override
	public Void visit(Subtract n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Signed)
	 */
	@Override
	public Void visit(Signed n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.TypeCast)
	 */
	@Override
	public Void visit(TypeCast n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.TypeSpecification)
	 */
	@Override
	public Void visit(TypeSpecification n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.UnaryMinus)
	 */
	@Override
	public Void visit(UnaryMinus n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.UnaryPlus)
	 */
	@Override
	public Void visit(UnaryPlus n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Unsigned)
	 */
	@Override
	public Void visit(Unsigned n) throws CException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.VariableDeclaration)
	 */
	@Override
	public Void visit(VariableDeclaration n) throws CException {

		current_ts = n.getTypeSpecification();
		List<ASTNode> decls = n.getDecls();

		for (ASTNode d : decls) {
			d.accept(this);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.ASTVisitor#visit(ast.Put)
	 */
	@Override
	public Void visit(Put n) throws CException {

		return null;
	}

}
