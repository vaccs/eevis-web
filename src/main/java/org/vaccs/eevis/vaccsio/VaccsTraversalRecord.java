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
public class VaccsTraversalRecord extends VaccsIORecord {

	private String order;

	/**
	 *
	 */
	public VaccsTraversalRecord() {
	}

	public VaccsTraversalRecord addOrder(String order) {
		this.order = "[" + order.substring(0,order.length()-1) + "]";
		return this;
	}

	/*
	 * @see vaccsio.VaccsIORecord#getStringFormat()
	 */
	@Override
	public String getStringFormat() {
		return "traversal"
				+ VaccsIORecord.typeSeparator + order;
	}

	/* (non-Javadoc)
	 * @see vaccsio.VaccsIORecord#write(java.io.FileOutputStream)
	 */
	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_ORDER).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(order.length()).array());
		out.write(order.getBytes());
	}

}
