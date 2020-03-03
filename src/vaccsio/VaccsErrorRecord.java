/**
 *
 */
package vaccsio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author carr
 *
 */
public class VaccsErrorRecord extends VaccsIORecord {

	private short errorCode;
  private String errorMsg;

	/**
	 *
	 */
	public VaccsErrorRecord() {
	}

	public VaccsErrorRecord addCompileError() {
		this.errorCode = 0;
		return this;
	}

	public VaccsErrorRecord addRuntimeError() {
		this.errorCode = 1;
		return this;
	}

	public VaccsErrorRecord addErrorMessage(String msg) {
		this.errorMsg = msg;
		return this;
	}
	/*
	 * @see vaccsio.VaccsIORecord#getStringFormat()
	 */
	@Override
	public String getStringFormat() {
		return "error"
				+ VaccsIORecord.typeSeparator + errorCode
        + VaccsIORecord.fieldSeparator + errorMsg;
	}

	/* (non-Javadoc)
	 * @see vaccsio.VaccsIORecord#write(java.io.FileOutputStream)
	 */
	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_ERROR).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(errorCode).array());
    out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(errorMsg.length()).array());
		out.write(errorMsg.getBytes());
	}

}
