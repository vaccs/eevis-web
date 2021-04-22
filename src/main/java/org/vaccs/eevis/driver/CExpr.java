/**
 *
 */
package org.vaccs.eevis.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import org.vaccs.eevis.ast.ASTGenerator;
import org.vaccs.eevis.ast.ASTNode;
import org.vaccs.eevis.parser.CLexer;
import org.vaccs.eevis.parser.CParser;
import org.vaccs.eevis.parser.BailErrorStrategy;
import org.vaccs.eevis.util.ArchitectureType;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.IlocProgram;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.util.X86_64Architecture;
import org.vaccs.eevis.visitor.CompileVisitor;
import org.vaccs.eevis.visitor.TypeVisitor;
import org.vaccs.eevis.vaccsio.VaccsErrorRecord;
import org.vaccs.eevis.vaccsio.VaccsIORecord;
import org.vaccs.eevis.vaccsio.VaccsIORecordFactory;

/**
 * @author carr
 *
 */
public class CExpr {

	public static class CBailLexer extends CLexer {
		public CBailLexer(CharStream input) {
			super(input);
		}

		public void recover(LexerNoViableAltException e) {
			throw new RuntimeException(e); // Bail out
		}
	}

	public static boolean dumpAscii = true;

	public static Memory processCodeFromFile(CharStream code) throws IOException {

		FileOutputStream fos = null;
		PrintWriter fw = null;
		VaccsIORecordFactory vioFactory = new VaccsIORecordFactory();

		if (dumpAscii)
			fw = new PrintWriter(code.getSourceName() + ".vaccs.ascii");
		else
			fos = new FileOutputStream(code.getSourceName() + ".vaccs");

		// create a lexer that feeds off of input CharStream
		CBailLexer lexer = new CBailLexer(code);
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// create a parser that feeds off the tokens buffer
		CParser parser = new CParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
		ParseTree t = null;
		try {
			t = parser.program();
		} catch (RuntimeException e) {
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addCompileError()
					.addErrorMessage("Parse error");

			if (CExpr.dumpAscii) {
				vior.write(fw);
				fw.close();
			} else {
				vior.write(fos);
				fos.close();
			}
			return null;
		}

		ASTNode ast = (ASTNode) t.accept(new ASTGenerator());

		CompileVisitor cv = null;
		try {
			ast.accept(new TypeVisitor(ArchitectureType.X86_64));
			cv = new CompileVisitor().addArchitecture(new X86_64Architecture());
			ast.accept(cv);
		} catch (CException e) {
			System.err.println(e.getMessage());
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addCompileError()
					.addErrorMessage(e.getMessage().toString());

			if (CExpr.dumpAscii) {
				vior.write(fw);
				fw.close();
			} else {
				vior.write(fos);
				fos.close();
			}
			return null;
		}

		IlocProgram prog;

		if (dumpAscii)
			prog = cv.getProgram().addPrintWriter(fw).addFileName(code.getSourceName()).addRoot(ast);
		else
			prog = cv.getProgram().addFileOutputStream(fos).addFileName(code.getSourceName()).addRoot(ast);

		try {
			return prog.evalProgram();
		} catch (CException e) {
			System.err.println(e.getMessage());
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addRuntimeError()
					.addErrorMessage(e.getMessage().toString());

			if (CExpr.dumpAscii) {
				vior.write(fw);
				fw.close();
			} else {
				vior.write(fos);
				fos.close();
			}
			return null;
		}

	}

	public static String processCodeFromString(String file, String input) throws IOException {
		return getAnalysisResult(file, CharStreams.fromString(input));
	}

	private static String getAnalysisResult(String file, CodePointCharStream code) {
		VaccsIORecordFactory vioFactory = new VaccsIORecordFactory();

		StringWriter sw = new StringWriter();

		// create a lexer that feeds off of input CharStream
		CBailLexer lexer = new CBailLexer(code);
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// create a parser that feeds off the tokens buffer
		CParser parser = new CParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
		ParseTree t = null;
		try {
			t = parser.program();
		} catch (RuntimeException e) {
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addCompileError()
					.addErrorMessage("Parse error");
			vior.write(sw);
			return sw.toString();
		}

		ASTNode ast = (ASTNode) t.accept(new ASTGenerator());

		CompileVisitor cv = null;
		try {
			ast.accept(new TypeVisitor(ArchitectureType.X86_64));
			cv = new CompileVisitor().addArchitecture(new X86_64Architecture());
			ast.accept(cv);
		} catch (CException e) {
			System.err.println(e.getMessage());
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addCompileError()
					.addErrorMessage(e.getMessage().toString());
			vior.write(sw);
			return sw.toString();
		}

		IlocProgram prog = cv.getProgram().addStringWriter(sw).addFileName(file).addRoot(ast);

		try {
			return prog.evalProgramReturnString();
		} catch (CException e) {
			System.err.println(e.getMessage());
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addRuntimeError()
					.addErrorMessage(e.getMessage().toString());

			vior.write(sw);
			return sw.toString();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			VaccsIORecord vior = ((VaccsErrorRecord) vioFactory.makeVaccIORecord("VaccsErrorRecord")).addRuntimeError()
					.addErrorMessage(e.getMessage().toString());

			vior.write(sw);
			return sw.toString();
		}

	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException, IOException {
		if (args.length > 0) {
			if (args[0].contentEquals("-bin")) {
				dumpAscii = false;
				processCodeFromFile(CharStreams.fromFileName(args[1]));
			} else
				processCodeFromFile(CharStreams.fromFileName(args[0]));
		} else {
			System.err.println("Usage: cexpr <file>");
			System.exit(-1);
		}
	}
}
