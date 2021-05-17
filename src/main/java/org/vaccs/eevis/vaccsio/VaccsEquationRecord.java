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
public class VaccsEquationRecord extends VaccsIORecord {

	private String equation;

	/**
	 *
	 */
	public VaccsEquationRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cSrcLineNum
	 */
	public String getEquation() {
		return equation;
	}

	/**
	 * @param sourceLine the sourceLine to set
	 */
	public void setEquation(String equation) {
		this.equation = equation;
	}

	/**
	 * @param sourceLine the sourceLine to set
	 */
	public VaccsEquationRecord addEquation(String equation) {
		this.equation = equation;
		return this;
	}

	@Override
	public String getStringFormat() {
		return "equation"
				+ VaccsIORecord.typeSeparator + equation;
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_EQUATION).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(equation.length()).array());
		out.write(equation.getBytes());
	}

}
