package org.vaccs.eevis.visitor;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

import org.vaccs.eevis.iloc.IlocInstruction;
import org.vaccs.eevis.iloc.AddInstruction;
import org.vaccs.eevis.iloc.DivideInstruction;
import org.vaccs.eevis.iloc.LoadIInstruction;
import org.vaccs.eevis.iloc.MultiplyInstruction;
import org.vaccs.eevis.iloc.OutputInstruction;
import org.vaccs.eevis.iloc.SubtractInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2bInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2iInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2lInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2sInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2ubInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2uiInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2ulInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2usInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2bInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2iInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2lInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2sInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2ubInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2uiInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2ulInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2usInstruction;
import org.vaccs.eevis.iloc.intcopy.I2bInstruction;
import org.vaccs.eevis.iloc.intcopy.I2iInstruction;
import org.vaccs.eevis.iloc.intcopy.I2lInstruction;
import org.vaccs.eevis.iloc.intcopy.I2sInstruction;
import org.vaccs.eevis.iloc.intcopy.I2ubInstruction;
import org.vaccs.eevis.iloc.intcopy.I2uiInstruction;
import org.vaccs.eevis.iloc.intcopy.I2ulInstruction;
import org.vaccs.eevis.iloc.intcopy.I2usInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2bInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2iInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2lInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2sInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2ubInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2uiInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2ulInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2usInstruction;
import org.vaccs.eevis.iloc.longcopy.L2bInstruction;
import org.vaccs.eevis.iloc.longcopy.L2iInstruction;
import org.vaccs.eevis.iloc.longcopy.L2lInstruction;
import org.vaccs.eevis.iloc.longcopy.L2sInstruction;
import org.vaccs.eevis.iloc.longcopy.L2ubInstruction;
import org.vaccs.eevis.iloc.longcopy.L2uiInstruction;
import org.vaccs.eevis.iloc.longcopy.L2ulInstruction;
import org.vaccs.eevis.iloc.longcopy.L2usInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2bInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2iInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2lInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2sInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2ubInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2uiInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2ulInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2usInstruction;
import org.vaccs.eevis.iloc.operand.Operand;
import org.vaccs.eevis.iloc.shortcopy.S2bInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2iInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2lInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2sInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2ubInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2uiInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2ulInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2usInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2bInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2iInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2lInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2sInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2ubInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2uiInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2ulInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2usInstruction;
import org.vaccs.eevis.util.ArchitectureType;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.vaccsio.VaccsIORecord;
import org.vaccs.eevis.vaccsio.VaccsIORecordFactory;
import org.vaccs.eevis.vaccsio.VaccsOutputRecord;
import org.vaccs.eevis.vaccsio.VaccsVariableAccessRecord;
import org.vaccs.eevis.value.ByteValue;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.value.CValueFactory;
import org.vaccs.eevis.value.IntValue;
import org.vaccs.eevis.value.LongValue;
import org.vaccs.eevis.value.ShortValue;
import org.vaccs.eevis.value.UnsignedByteValue;
import org.vaccs.eevis.value.UnsignedIntValue;
import org.vaccs.eevis.value.UnsignedLongValue;
import org.vaccs.eevis.value.UnsignedShortValue;
import org.vaccs.eevis.driver.CExpr;

public class EvalVisitor implements IlocVisitor<CValue> {

	private Memory memory;
	private CValueFactory cvFactory;
	private VaccsIORecordFactory viorFactory = new VaccsIORecordFactory();
	private FileOutputStream fos;
	private PrintWriter fw;
	private StringWriter sw = null;
	private static ArchitectureType archType;
	private VaccsIORecord vior;
	private long eventNum = 2; // event 1 is the call to main
	private String fileName;

	public EvalVisitor() throws CException {
		cvFactory = new CValueFactory();
	}

	public EvalVisitor addMemory(Memory mem) throws CException {
		memory = mem;
		return this;
	}

	public EvalVisitor addArchitecture(ArchitectureType arch) throws CException {
		archType = arch;
		return this;
	}

	public EvalVisitor addFileOutputStream(FileOutputStream stream) {
		fos = stream;
		return this;
	}

	public EvalVisitor addPrintWriter(PrintWriter writer) {
		fw = writer;
		return this;
	}

	public EvalVisitor addFileName(String fn) {
		fileName = fn;
		return this;
	}

	/**
	 * @return the archType
	 */
	public static ArchitectureType getArchType() throws CException {
		return archType;
	}

	/**
	 * @param archType the archType to set
	 */
	public static void setArchType(ArchitectureType arch) throws CException {
		archType = arch;
	}

	public long getEventNum() {
		return eventNum;
	}

	public Memory getMemory() {
		return memory;
	}

	public Number getVariableValue(String var) throws CException {
		return memory.get(var).getJavaValue();
	}

	private void addVariableAccessRecord(IlocInstruction inst, CValue result) {

		vior = ((VaccsVariableAccessRecord) viorFactory.makeVaccIORecord("VaccsVariableAccessRecord"))
				.addEventNum(eventNum++).addAstId((short) inst.getAstId()).addIlocId((short) inst.getIlocId())
				.addName(inst.getDest().getName()).addType(result.getType()).addValue(result.getJavaValue().toString());

		try {
			if (sw != null)
				vior.write(sw);
			else if (CExpr.dumpAscii)
				vior.write(fw);
			else
				vior.write(fos);
		} catch (IOException e) {
			System.err.println("Exception in EvalVisitor: " + e);
			System.exit(-1);
		}
	}

	@Override
	public CValue visit(AddInstruction inst) throws CException {
		CValue rValue1 = inst.getSrc1().getValue(memory);
		CValue rValue2 = inst.getSrc2().getValue(memory);

		CValue result = rValue1.add(rValue2);

		memory.put(inst.getDest().getName(), result);

		addVariableAccessRecord(inst, result);

		return result;
	}

	@Override
	public CValue visit(B2bInstruction inst) throws CException {
		byte newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2iInstruction inst) throws CException {
		int newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2lInstruction inst) throws CException {
		long newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2sInstruction inst) throws CException {
		short newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2ubInstruction inst) throws CException {
		int newVal = (int) ((ByteValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2uiInstruction inst) throws CException {
		long newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(B2ulInstruction inst) throws CException {
		long newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(B2usInstruction inst) throws CException {
		int newVal = ((ByteValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(DivideInstruction inst) throws CException {
		CValue rValue1 = inst.getSrc1().getValue(memory);
		CValue rValue2 = inst.getSrc2().getValue(memory);

		CValue result = rValue1.div(rValue2);

		memory.put(inst.getDest().getName(), result);

		addVariableAccessRecord(inst, result);

		return result;
	}

	@Override
	public CValue visit(I2bInstruction inst) throws CException {
		byte newVal = ((IntValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2iInstruction inst) throws CException {
		int newVal = ((IntValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2lInstruction inst) throws CException {
		long newVal = ((IntValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2sInstruction inst) throws CException {
		short newVal = ((IntValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2ubInstruction inst) throws CException {
		int newVal = ((IntValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(I2uiInstruction inst) throws CException {
		long newVal = ((IntValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2ulInstruction inst) throws CException {
		long newVal = ((IntValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(I2usInstruction inst) throws CException {
		int newVal = ((IntValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2bInstruction inst) throws CException {
		byte newVal = ((LongValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2iInstruction inst) throws CException {
		int newVal = ((LongValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2lInstruction inst) throws CException {
		long newVal = ((LongValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2sInstruction inst) throws CException {
		short newVal = ((LongValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2ubInstruction inst) throws CException {
		int newVal = ((LongValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);
		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(L2uiInstruction inst) throws CException {
		long newVal = ((LongValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(L2ulInstruction inst) throws CException {
		long newVal = ((LongValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(L2usInstruction inst) throws CException {
		int newVal = ((LongValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(MultiplyInstruction inst) throws CException {
		CValue rValue1 = inst.getSrc1().getValue(memory);
		CValue rValue2 = inst.getSrc2().getValue(memory);

		CValue result = rValue1.mul(rValue2);

		memory.put(inst.getDest().getName(), result);

		addVariableAccessRecord(inst, result);

		return result;
	}

	@Override
	public CValue visit(S2bInstruction inst) throws CException {
		byte newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2iInstruction inst) throws CException {
		int newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(S2lInstruction inst) throws CException {
		long newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2sInstruction inst) throws CException {
		short newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2ubInstruction inst) throws CException {
		int newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2uiInstruction inst) throws CException {
		long newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2ulInstruction inst) throws CException {
		long newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(S2usInstruction inst) throws CException {
		int newVal = ((ShortValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(SubtractInstruction inst) throws CException {
		CValue rValue1 = inst.getSrc1().getValue(memory);
		CValue rValue2 = inst.getSrc2().getValue(memory);

		CValue result = rValue1.sub(rValue2);

		memory.put(inst.getDest().getName(), result);

		addVariableAccessRecord(inst, result);

		return result;
	}

	@Override
	public CValue visit(Ub2bInstruction inst) throws CException {
		byte newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2iInstruction inst) throws CException {
		int newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2lInstruction inst) throws CException {
		long newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2sInstruction inst) throws CException {
		short newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2ubInstruction inst) throws CException {
		int newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2uiInstruction inst) throws CException {
		long newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2ulInstruction inst) throws CException {
		long newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ub2usInstruction inst) throws CException {
		int newVal = ((UnsignedByteValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);
		return val;
	}

	@Override
	public CValue visit(Ui2bInstruction inst) throws CException {
		byte newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2iInstruction inst) throws CException {
		int newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2lInstruction inst) throws CException {
		long newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2sInstruction inst) throws CException {
		short newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2ubInstruction inst) throws CException {
		int newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2uiInstruction inst) throws CException {
		long newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2ulInstruction inst) throws CException {
		long newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ui2usInstruction inst) throws CException {
		int newVal = ((UnsignedIntValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2bInstruction inst) throws CException {
		byte newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2iInstruction inst) throws CException {
		int newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2lInstruction inst) throws CException {
		long newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2sInstruction inst) throws CException {
		short newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2ubInstruction inst) throws CException {
		int newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2uiInstruction inst) throws CException {
		long newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2ulInstruction inst) throws CException {
		long newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Ul2usInstruction inst) throws CException {
		int newVal = ((UnsignedLongValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2bInstruction inst) throws CException {
		byte newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getByteValue();
		ByteValue val = ((ByteValue) cvFactory.makeCValue("ByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2iInstruction inst) throws CException {
		int newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getIntValue();
		IntValue val = ((IntValue) cvFactory.makeCValue("IntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2lInstruction inst) throws CException {
		long newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getLongValue();
		LongValue val = ((LongValue) cvFactory.makeCValue("LongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2sInstruction inst) throws CException {
		short newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getShortValue();
		ShortValue val = ((ShortValue) cvFactory.makeCValue("ShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2ubInstruction inst) throws CException {
		int newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getUnsignedByteValue();
		UnsignedByteValue val = ((UnsignedByteValue) cvFactory.makeCValue("UnsignedByteValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2uiInstruction inst) throws CException {
		long newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getUnsignedIntValue();
		UnsignedIntValue val = ((UnsignedIntValue) cvFactory.makeCValue("UnsignedIntValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2ulInstruction inst) throws CException {
		long newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getUnsignedLongValue();
		UnsignedLongValue val = ((UnsignedLongValue) cvFactory.makeCValue("UnsignedLongValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(Us2usInstruction inst) throws CException {
		int newVal = ((UnsignedShortValue) inst.getSrc1().getValue(memory)).getUnsignedShortValue();
		UnsignedShortValue val = ((UnsignedShortValue) cvFactory.makeCValue("UnsignedShortValue")).addValue(newVal);
		memory.put(inst.getDest().getName(), val);

		addVariableAccessRecord(inst, val);

		return val;
	}

	@Override
	public CValue visit(OutputInstruction inst) throws CException {
		String output = memory.get(inst.getSrc1().getName()).getJavaValue().toString();

		vior = ((VaccsOutputRecord) viorFactory.makeVaccIORecord("VaccsOutputRecord")).addEventNum(eventNum++)
				.addOutput(output);

		try {
			if (sw != null)
				vior.write(sw);
			else if (CExpr.dumpAscii)
				vior.write(fw);
			else
				vior.write(fos);

		} catch (IOException e) {
			System.err.println("Exception in EvalVisitor: " + e);
			System.exit(-1);
		}

		System.out.println(memory.get(inst.getSrc1().getName()).getJavaValue());
		return null;
	}

	private String getTypeValueString(int type) {
		String typeString = "";
		switch (type) {
			case CTypeTables.CBYTE:
				typeString = "ByteValue";
				break;
			case CTypeTables.CINT:
				typeString = "IntValue";
				break;
			case CTypeTables.CLONG:
				typeString = "LongValue";
				break;
			case CTypeTables.CSHORT:
				typeString = "ShortValue";
				break;
			case CTypeTables.CUBYTE:
				typeString = "UnsignedByteValue";
				break;
			case CTypeTables.CUINT:
				typeString = "UnsignedIntValue";
				break;
			case CTypeTables.CULONG:
				typeString = "UnsignedLongValue";
				break;
			case CTypeTables.CUSHORT:
				typeString = "UnsignedShortValue";
				break;
		}

		return typeString;
	}

	private void addConstValue(CValue constVal, Operand op) {
		switch (op.getType()) {
			case CTypeTables.CBYTE:
				((ByteValue) constVal).addValue(Byte.parseByte(op.getName()));
				break;
			case CTypeTables.CINT:
				((IntValue) constVal).addValue(Integer.parseInt(op.getName()));
				break;
			case CTypeTables.CLONG:
				((LongValue) constVal).addValue(Long.parseLong(op.getName()));
				break;
			case CTypeTables.CSHORT:
				((ShortValue) constVal).addValue(Short.parseShort(op.getName()));
				break;
			case CTypeTables.CUBYTE:
				((UnsignedByteValue) constVal).addValue(Integer.parseInt(op.getName()));
				break;
			case CTypeTables.CUINT:
				((UnsignedIntValue) constVal).addValue(Long.parseLong(op.getName()));
				break;
			case CTypeTables.CULONG:
				((UnsignedLongValue) constVal).addValue(Long.parseLong(op.getName()));
				break;
			case CTypeTables.CUSHORT:
				((UnsignedShortValue) constVal).addValue(Integer.parseInt(op.getName()));
				break;

		}
	}

	@Override
	public CValue visit(LoadIInstruction inst) throws CException {
		CValue constVal = cvFactory.makeCValue(getTypeValueString(inst.getSrc1().getType()));
		addConstValue(constVal, inst.getSrc1());
		memory.put(inst.getDest().getName(), constVal);

		if (!inst.getInitialization()) {
			addVariableAccessRecord(inst, constVal);
		}

		return constVal;
	}

	public EvalVisitor addStringWriter(StringWriter sw) {
		this.sw = sw;
		return this;
	}

}
