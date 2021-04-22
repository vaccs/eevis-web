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
public class VaccsOutputRecord extends VaccsIORecord {

	private String output;
	private long eventNum;
	/**
	 *
	 */
	public VaccsOutputRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}
	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @param output the output to set
	 */
	public VaccsOutputRecord addOutput(String output) {
		this.output = output;
		return this;
	}

	public long getEventNum() {
		return eventNum;
	}

	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}

	public VaccsOutputRecord addEventNum(long eventNum) {
		this.eventNum = eventNum;
		return this;
	}


	@Override
	public String getStringFormat() {
		return "output"
				+ VaccsIORecord.typeSeparator + eventNum
				+ VaccsIORecord.fieldSeparator + output;
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		if (org.vaccs.eevis.driver.CExpr.dumpAscii)
			System.out.println(getStringFormat());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_OUTPUT).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(eventNum).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(output.length()).array());
		out.write(output.getBytes());
	}

}
