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
public class VaccsReturnAddressRecord extends VaccsIORecord {

	private long dynamicLink;
	private long returnAddress;
	private String functionName;

	/**
	 *
	 */
	public VaccsReturnAddressRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dynamicLink
	 */
	public long getDynamicLink() {
		return dynamicLink;
	}

	/**
	 * @return the returnAddress
	 */
	public long getReturnAddress() {
		return returnAddress;
	}

	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param dynamicLink the dynamicLink to set
	 */
	public void setDynamicLink(long dynamicLink) {
		this.dynamicLink = dynamicLink;
	}

	/**
	 * @param returnAddress the returnAddress to set
	 */
	public void setReturnAddress(long returnAddress) {
		this.returnAddress = returnAddress;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * @param dynamicLink the dynamicLink to add
	 */
	public VaccsReturnAddressRecord addDynamicLink(long dynamicLink) {
		this.dynamicLink = dynamicLink;
		return this;
	}

	/**
	 * @param returnAddress the returnAddress to add
	 */
	public VaccsReturnAddressRecord addReturnAddress(long returnAddress) {
		this.returnAddress = returnAddress;
		return this;
	}

	/**
	 * @param functionName the functionName to add
	 */
	public VaccsReturnAddressRecord addFunctionName(String functionName) {
		this.functionName = functionName;
		return this;
	}

	@Override
	public String getStringFormat() {
		return "return_address"
				+ VaccsIORecord.typeSeparator + functionName
				+ VaccsIORecord.fieldSeparator + dynamicLink
				+ VaccsIORecord.fieldSeparator + returnAddress;
	}

	@Override
	public void write(FileOutputStream out) throws IOException {
		if (driver.CExpr.dumpAscii)
			System.out.println(getStringFormat());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_RETURN_ADDR).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(dynamicLink).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(returnAddress).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(functionName.length()).array());
		out.write(functionName.getBytes());

	}

}
