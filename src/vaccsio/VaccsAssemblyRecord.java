/**
 *
 */
package vaccsio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import java.util.List;
import java.util.ArrayList;
import util.CTypeTables;

/**
 * @author carr
 *
 */
public class VaccsAssemblyRecord extends VaccsIORecord {

	private short ilocId;
	private short astId;
	private String inst;
	private boolean promotion = false;
	private short conversionRule = CTypeTables.NO_RULE;
	private int destType;
	private List<Integer> srcTypes;

	/**
	 * @return the ilocId
	 */
	public short getIlocId() {
		return ilocId;
	}

	/**
	 * @return the astId
	 */
	public short getAstId() {
		return astId;
	}

	/**
	 * @return the inst
	 */
	public String getInst() {
		return inst;
	}

	public int getDestType() {
		return destType;
	}

	public int getSrcType(int operand) {
		return srcTypes.get(operand);
	}

	public List<Integer> getSrcTypes() {
		return srcTypes;
	}

	public int getNumSrcTypes() {
		return srcTypes.size();
	}

	/**
	 * @param ilocId the ilocId to set
	 */
	public void setIlocId(short ilocId) {
		this.ilocId = ilocId;
	}

	public VaccsAssemblyRecord addIlocId(short ilocId) {
		this.ilocId = ilocId;
		return this;
	}

	/**
	 * @param astId the ilocId to set
	 */
	public void setAstId(short astId) {
		this.astId = astId;
	}

	public VaccsAssemblyRecord addAstId(short astId) {
		this.astId = astId;
		return this;
	}

	/**
	 * @param inst the inst to set
	 */
	public VaccsAssemblyRecord addInst(String inst) {
		this.inst = inst;
		return this;
	}

	/**
	 * @param inst the inst to set
	 */
	public VaccsAssemblyRecord addPromotion(boolean promotion) {
		this.promotion = promotion;
		return this;
	}

	/**
	 * @param inst the inst to set
	 */
	public VaccsAssemblyRecord addConversionRule(short rule) {
		this.conversionRule = rule;
		return this;
	}

	/**
	 * @param inst the inst to set
	 */
	public void setInst(String inst) {
		this.inst = inst;
	}

	public VaccsAssemblyRecord addDestType(int type) {
		destType = type;
		return this;
	}

	public VaccsAssemblyRecord addSrcType(int type) {
		srcTypes.add(type);
		return this;
	}

	public VaccsAssemblyRecord addSrcTypes(List<Integer> types) {
		if (types.size() > 0)
			srcTypes.add(types.get(0));
		if (types.size() > 1)
			srcTypes.add(types.get(1));
		return this;
	}

	/**
	 *
	 */
	public VaccsAssemblyRecord() {
		srcTypes = new ArrayList<Integer>();
	}


	private String stringifyList(List<Integer> l) {
		String ls = "[";
		for (Integer i : l)
			ls += (CTypeTables.typeName[i] + ", ");

		return ls.substring(0, (ls.length() > 2 ? ls.length()-2 : 1)) + "]";
	}

	@Override
	public String getStringFormat() {
		return "assembly"
					+ VaccsIORecord.typeSeparator + ilocId
					+ VaccsIORecord.fieldSeparator + astId
					+ VaccsIORecord.fieldSeparator + inst
					+ VaccsIORecord.fieldSeparator + (promotion ?  CTypeTables.PROMOTION : conversionRule)
					+ VaccsIORecord.fieldSeparator + CTypeTables.typeName[destType]
					+ VaccsIORecord.fieldSeparator + stringifyList(srcTypes);
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_ASM).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(ilocId).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(astId).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(inst.length()).array());
		out.write(inst.getBytes());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(conversionRule).array());
		out.write(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.nativeOrder()).putInt(destType).array());
		out.write(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.nativeOrder()).putInt(srcTypes.size()).array());
		out.write(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.nativeOrder()).putInt(srcTypes.get(0)).array());
		if (srcTypes.size() == 2)
			out.write(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.nativeOrder()).putInt(srcTypes.get(0)).array());
	}

}
