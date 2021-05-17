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
public class VaccsReturnRecord extends VaccsIORecord {

	private long eventNum;

	/**
	 *
	 */
	public VaccsReturnRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the eventNum
	 */
	public long getEventNum() {
		return eventNum;
	}

	/**
	 * @param eventNum the eventNum to set
	 */
	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}

	/**
	 * @param eventNum the eventNum to set
	 */
	public VaccsReturnRecord addEventNum(long eventNum) {
		this.eventNum = eventNum;
		return this;
	}

	@Override
	public String getStringFormat() {
		return "return"
				+ VaccsIORecord.typeSeparator + eventNum;
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		if (org.vaccs.eevis.driver.CExpr.dumpAscii)
			System.out.println(getStringFormat());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_RETURN).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(eventNum).array());
	}

}
