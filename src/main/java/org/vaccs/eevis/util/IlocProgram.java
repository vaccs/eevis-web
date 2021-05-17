/**
 *
 */
package org.vaccs.eevis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.vaccs.eevis.ast.ASTNode;
import org.vaccs.eevis.driver.CExpr;
import org.vaccs.eevis.iloc.IlocInstruction;
import org.vaccs.eevis.vaccsio.VaccsArchitectureRecord;
import org.vaccs.eevis.vaccsio.VaccsAssemblyRecord;
import org.vaccs.eevis.vaccsio.VaccsEquationRecord;
import org.vaccs.eevis.vaccsio.VaccsFunctionInvocationRecord;
import org.vaccs.eevis.vaccsio.VaccsIORecord;
import org.vaccs.eevis.vaccsio.VaccsIORecordFactory;
import org.vaccs.eevis.vaccsio.VaccsReturnAddressRecord;
import org.vaccs.eevis.vaccsio.VaccsTraversalRecord;
import org.vaccs.eevis.visitor.EmitASTRecordVisitor;
import org.vaccs.eevis.visitor.EmitVarDeclRecordVisitor;
import org.vaccs.eevis.visitor.EquationSourceVisitor;
import org.vaccs.eevis.visitor.EvalVisitor;
import org.vaccs.eevis.visitor.IlocSourceVisitor;

/**
 * @author carr
 *
 */
public class IlocProgram extends ArrayList<IlocInstruction> {

	private FileOutputStream fos = null;
	private PrintWriter fw = null;
	private StringWriter sw = null;
	private ASTNode root;
	private String fileName = null;

	public IlocProgram addFileName(String fn) {
		this.fileName = fn;
		return this;
	}

	public IlocProgram addPrintWriter(PrintWriter fw) {
		this.fw = fw;
		return this;
	}

	public IlocProgram addFileOutputStream(FileOutputStream fos) {
		this.fos = fos;
		return this;
	}

	public IlocProgram addRoot(ASTNode root) {
		this.root = root;
		return this;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 2738725695908896111L;

	public void printProgram() throws CException {
		IlocSourceVisitor isv = new IlocSourceVisitor();

		for (IlocInstruction inst : this)
			System.out.println(inst.accept(isv));

	}

	public Memory evalProgram() throws CException, IOException {

		VaccsIORecordFactory vioFactory = new VaccsIORecordFactory();

		// write an Architecture record

		VaccsIORecord vior = ((VaccsArchitectureRecord) vioFactory.makeVaccIORecord("VaccsArchitectureRecord"))
				.addArchitectureType(VaccsArchitectureRecord.X86_64);

		if (sw != null)
			vior.write(sw);
		else if (CExpr.dumpAscii)
			vior.write(fw);
		else
			vior.write(fos);

		EquationSourceVisitor esv = new EquationSourceVisitor();
		vior = ((VaccsEquationRecord) vioFactory.makeVaccIORecord("VaccsEquationRecord"))
				.addEquation((String) root.accept(esv));
		if (sw != null)
			vior.write(sw);
		else if (CExpr.dumpAscii)
			vior.write(fw);
		else
			vior.write(fos);

		EmitVarDeclRecordVisitor vdrv = new EmitVarDeclRecordVisitor().addFOS(fos).addFW(fw).addSW(sw);
		root.accept(vdrv);

		EmitASTRecordVisitor earv = (new EmitASTRecordVisitor()).addFOS(fos).addFW(fw).addSW(sw);
		String traversal = (String) root.accept(earv);

		vior = ((VaccsTraversalRecord) vioFactory.makeVaccIORecord("VaccsTraversalRecord")).addOrder(traversal);

		if (sw != null)
			vior.write(sw);
		else if (CExpr.dumpAscii)
			vior.write(fw);
		else
			vior.write(fos);

		// write the asm (iloc) records

		short lineNum = 0;
		IlocSourceVisitor isv = new IlocSourceVisitor();
		for (IlocInstruction inst : this) {
			if (!inst.getInitialization()) {
				vior = ((VaccsAssemblyRecord) vioFactory.makeVaccIORecord("VaccsAssemblyRecord"))
						.addIlocId(inst.getIlocId()).addAstId(inst.getAstId()).addInst((String) inst.accept(isv))
						.addPromotion(inst.getPromotion()).addDestType(inst.getDestType())
						.addSrcTypes(inst.getSrcTypes()).addConversionRule(inst.getConversionRule());

				if (sw != null)
					vior.write(sw);
				else if (CExpr.dumpAscii)
					vior.write(fw);
				else
					vior.write(fos);
			}
		}

		// write main function call and return address

		vior = ((VaccsFunctionInvocationRecord) vioFactory.makeVaccIORecord("VaccsFunctionInvocationRecord"))
				.addAddress(1).addcFuncPath(fileName).addcInvPath("__NOCSOURCE___").addEventNum(1)
				.addFuncLineNum((short) 1).addFuncName("main").addInvLineNum((short) 0);
		if (sw != null)
			vior.write(sw);
		else if (CExpr.dumpAscii)
			vior.write(fw);
		else
			vior.write(fos);

		vior = ((VaccsReturnAddressRecord) vioFactory.makeVaccIORecord("VaccsReturnAddressRecord")).addDynamicLink(0)
				.addFunctionName("main").addReturnAddress(0);
		if (sw != null)
			vior.write(sw);
		else if (CExpr.dumpAscii)
			vior.write(fw);
		else
			vior.write(fos);

		EvalVisitor ev = new EvalVisitor().addMemory(new Memory()).addArchitecture(new X86_64Architecture())
				.addFileOutputStream(fos).addPrintWriter(fw).addStringWriter(sw).addFileName(fileName);

		for (IlocInstruction inst : this)
			inst.accept(ev);

		if (sw == null)
			System.out.println("Value = " + ev.getVariableValue(get(size() - 1).getDest().getName()));

		if (sw != null) {
		} else if (CExpr.dumpAscii)
			fw.close();
		else
			fos.close();

		return ev.getMemory();
	}

	public IlocProgram addStringWriter(StringWriter sw) {
		this.sw = sw;
		return this;
	}

	public String evalProgramReturnString() throws CException, IOException {
		evalProgram();
		return sw.getBuffer().toString();
	}
}
