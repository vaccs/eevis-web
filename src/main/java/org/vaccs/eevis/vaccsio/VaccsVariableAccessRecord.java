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
public class VaccsVariableAccessRecord extends VaccsIORecord {

	private long eventNum; /* the event number */
	private short astId; /* the AST Node for the update */
	private short ilocId; /* the iloc instruction Id for the update*/
	private String type; /* the variable type */
	private String name; /* the name of the variable */
	private String value; /* the variable value */

	/**
	 *
	 */
	public VaccsVariableAccessRecord() {
	}

	/**
	 * @return the eventNum
	 */
	public long getEventNum() {
		return eventNum;
	}

	/**
	 * @return the astId
	 */
	public short getAstId() {
		return astId;
	}


	/**
	 * @return the astId
	 */
	public short getIlocId() {
		return ilocId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param eventNum the eventNum to set
	 */
	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}

	/**
	 * @param astId the astId to set
	 */
	public void setAstId(short astId) {
		this.astId = astId;
	}


	/**
	 * @param ilocId the astId to set
	 */
	public void setIlocId(short ilocId) {
		this.ilocId = ilocId;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param eventNum the eventNum to add
	 */
	public VaccsVariableAccessRecord addEventNum(long eventNum) {
		this.eventNum = eventNum;
		return this;
	}

	/**
	 * @param astId the astId to add
	 */
	public VaccsVariableAccessRecord addAstId(short astId) {
		this.astId = astId;
		return this;
	}


	/**
	 * @param ilocId the astId to add
	 */
	public VaccsVariableAccessRecord addIlocId(short ilocId) {
		this.ilocId = ilocId;
		return this;
	}

	/**
	 * @param type the type to add
	 */
	public VaccsVariableAccessRecord addType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * @param name the name to add
	 */
	public VaccsVariableAccessRecord addName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param value the value to add
	 */
	public VaccsVariableAccessRecord addValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String getStringFormat() {
		return "variable_access"
				+ VaccsIORecord.typeSeparator + eventNum
				+ VaccsIORecord.fieldSeparator + astId
				+ VaccsIORecord.fieldSeparator + ilocId
				+ VaccsIORecord.fieldSeparator + type
				+ VaccsIORecord.fieldSeparator + name
				+ VaccsIORecord.fieldSeparator + value;
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		if (org.vaccs.eevis.driver.CExpr.dumpAscii)
			System.out.println(getStringFormat());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_VAR_ACCESS).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(eventNum).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(astId).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(ilocId).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(type.length()).array());
		out.write(type.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(name.length()).array());
		out.write(name.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(value.length()).array());
		out.write(value.getBytes());
	}

}
