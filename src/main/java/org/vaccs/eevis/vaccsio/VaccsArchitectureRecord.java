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
public class VaccsArchitectureRecord extends VaccsIORecord {

	public static final byte I386 = 0;
	public static final byte X86_64 = 1;

	private byte architectureType;

	/**
	 *
	 */
	public VaccsArchitectureRecord() {
	}

	public VaccsArchitectureRecord addArchitectureType(byte aType) {
		architectureType = aType;
		return this;
	}

	/**
	 * @return the architectureType
	 */
	public byte getArchitectureType() {
		return architectureType;
	}

	/**
	 * @param architectureType the architectureType to set
	 */
	public void setArchitectureType(byte architectureType) {
		this.architectureType = architectureType;
	}

	@Override
	public String getStringFormat() {
		return "architecture"
				+ VaccsIORecord.typeSeparator + (architectureType == I386 ? "i386" : "x86_64");
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_ARCH).array());
		out.write(architectureType);
	}
}
