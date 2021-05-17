/**
 *
 */
package org.vaccs.eevis.vaccsio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author carr
 *
 */
public class VaccsFunctionInvocationRecord extends VaccsIORecord {

	private long eventNum;
	private short funcLineNum;
	private short invLineNum;
	private String cFuncPath;
	private String cInvPath;
	private String funcName;
	private long address;
	/**
	 *
	 */
	public VaccsFunctionInvocationRecord() {
	}
	/**
	 * @return the eventNum
	 */
	public long getEventNum() {
		return eventNum;
	}
	/**
	 * @return the funcLineNum
	 */
	public short getFuncLineNum() {
		return funcLineNum;
	}
	/**
	 * @return the invLineNum
	 */
	public short getInvLineNum() {
		return invLineNum;
	}
	/**
	 * @return the cFuncPath
	 */
	public String getcFuncPath() {
		return cFuncPath;
	}
	/**
	 * @return the cInvPath
	 */
	public String getcInvPath() {
		return cInvPath;
	}
	/**
	 * @return the funcName
	 */
	public String getFuncName() {
		return funcName;
	}
	/**
	 * @return the address
	 */
	public long getAddress() {
		return address;
	}

	public String getBaseFuncFile() {
		return cFuncPath.substring(cFuncPath.lastIndexOf('/')+1);
	}
	/**
	 * @param eventNum the eventNum to set
	 */
	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}
	/**
	 * @param funcLineNum the funcLineNum to set
	 */
	public void setFuncLineNum(short funcLineNum) {
		this.funcLineNum = funcLineNum;
	}
	/**
	 * @param invLineNum the invLineNum to set
	 */
	public void setInvLineNum(short invLineNum) {
		this.invLineNum = invLineNum;
	}
	/**
	 * @param cFuncPath the cFuncPath to set
	 */
	public void setcFuncPath(String cFuncPath) {
		this.cFuncPath = cFuncPath;
	}
	/**
	 * @param cInvPath the cInvPath to set
	 */
	public void setcInvPath(String cInvPath) {
		this.cInvPath = cInvPath;
	}
	/**
	 * @param funcName the funcName to set
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(long address) {
		this.address = address;
	}

	/**
	 * @param eventNum the eventNum to add
	 */
	public VaccsFunctionInvocationRecord addEventNum(long eventNum) {
		this.eventNum = eventNum;
		return this;
	}
	/**
	 * @param funcLineNum the funcLineNum to add
	 */
	public VaccsFunctionInvocationRecord addFuncLineNum(short funcLineNum) {
		this.funcLineNum = funcLineNum;
		return this;
	}
	/**
	 * @param invLineNum the invLineNum to add
	 */
	public VaccsFunctionInvocationRecord addInvLineNum(short invLineNum) {
		this.invLineNum = invLineNum;
		return this;
	}
	/**
	 * @param cFuncPath the cFuncPath to add
	 */
	public VaccsFunctionInvocationRecord addcFuncPath(String cFuncPath) {
		this.cFuncPath = cFuncPath;
		return this;
	}
	/**
	 * @param cInvPath the cInvPath to add
	 */
	public VaccsFunctionInvocationRecord addcInvPath(String cInvPath) {
		this.cInvPath = cInvPath;
		return this;
	}
	/**
	 * @param funcName the funcName to add
	 */
	public VaccsFunctionInvocationRecord addFuncName(String funcName) {
		this.funcName = funcName;
		return this;
	}
	/**
	 * @param address the address to add
	 */
	public VaccsFunctionInvocationRecord addAddress(long address) {
		this.address = address;
		return this;
	}
	@Override
	public String getStringFormat() {
//		if (invLineNum == 0 && funcLineNum == 0)
//			return null;
//		else
			return "function_invocation"
					+ VaccsIORecord.typeSeparator + eventNum
					+ VaccsIORecord.fieldSeparator + invLineNum
					+ VaccsIORecord.fieldSeparator + getBaseFuncFile() + "`" + funcName
					+ VaccsIORecord.fieldSeparator + (address == 0 ? VaccsIORecord.NULL_ADDRESS_STRING : Long.toHexString(address));
	}
	@Override
	public void write(FileOutputStream out) throws IOException {
		if (org.vaccs.eevis.driver.CExpr.dumpAscii)
			System.out.println(getStringFormat());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_FUNCTION_INV).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(eventNum).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(funcLineNum).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(invLineNum).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(cFuncPath.length()).array());
		out.write(cFuncPath.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(cInvPath.length()).array());
		out.write(cInvPath.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(funcName.length()).array());
		out.write(funcName.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(address).array());
	}
}
