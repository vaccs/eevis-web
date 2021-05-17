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
public class VaccsVarDeclRecord extends VaccsIORecord {

	private String name;
	private String type;
	private String value;

	/**
	 *
	 */
	public VaccsVarDeclRecord() {
	}

	public VaccsVarDeclRecord addName(String name) {
		this.name = name;
		return this;
	}

	public VaccsVarDeclRecord addType(String type) {
		this.type = type;
		return this;
	}


	public VaccsVarDeclRecord addValue(String value) {
		this.value = value;
		return this;
	}
	/*
	 * @see vaccsio.VaccsIORecord#getStringFormat()
	 */
	@Override
	public String getStringFormat() {
		return "var_decl_node"
				+ VaccsIORecord.typeSeparator + name
				+ VaccsIORecord.fieldSeparator + type
				+ VaccsIORecord.fieldSeparator + value;
	}

	/* (non-Javadoc)
	 * @see vaccsio.VaccsIORecord#write(java.io.FileOutputStream)
	 */
	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_VARDECL).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(name.length()).array());
		out.write(name.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(type.length()).array());
		out.write(type.getBytes());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(value.length()).array());
		out.write(value.getBytes());
	}

}
