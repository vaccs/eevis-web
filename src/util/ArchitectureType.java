/**
 * 
 */
package util;

import java.math.BigInteger;

/**
 * @author carr
 *
 */
public abstract class ArchitectureType {
	public static final int IA32 = 0;
	public static final int X86_64 = 1;
	
	protected BigInteger minByteValue =  new BigInteger("-128");
	protected BigInteger maxByteValue =  new BigInteger("127");
	protected BigInteger maxUnsignedByteValue = new BigInteger("255");

	protected BigInteger minShortValue =  new BigInteger("-32768");
	protected BigInteger maxShortValue =  new BigInteger("32767");
	protected BigInteger maxUnsignedShortValue = new BigInteger("65535");

	protected BigInteger minIntegerValue =  new BigInteger("-2147483648");
	protected BigInteger maxIntegerValue =  new BigInteger("2147483647");
	protected BigInteger maxUnsignedIntegerValue = new BigInteger("4294967295");

	protected BigInteger minLongValue;
	protected BigInteger maxLongValue;
	protected BigInteger maxUnsignedLongValue;	
	
	public int byteSize() {
		return 1;
	}
	
	public int shortSize() {
		return 2;
	}
	
	public int intSize() {
		return 4;
	}
	
	public abstract int longSize();
	
	public BigInteger getMinByteValue() {
		return minByteValue;
	}

	
	public BigInteger getMaxByteValue() {
		return maxByteValue;
	}

	
	public BigInteger getMaxUnsignedByteValue() {
		return maxUnsignedByteValue;
	}

	
	public BigInteger getMinShortValue() {
		return minShortValue;
	}

	
	public BigInteger getMaxShortValue() {
		return maxShortValue;
	}

	
	public BigInteger getMaxUnsignedShortValue() {
		return maxUnsignedShortValue;
	}

	
	public BigInteger getMinIntValue() {
		return minIntegerValue;
	}

	
	public BigInteger getMaxIntValue() {
		return maxIntegerValue;
	}

	
	public BigInteger getMaxUnsignedIntValue() {
		return maxUnsignedIntegerValue;
	}

	
	public BigInteger getMinLongValue() {
		return minLongValue;
	}

	
	public BigInteger getMaxLongValue() {
		return maxLongValue;
	}

	
	public BigInteger getMaxUnsignedLongValue() {
		return maxUnsignedLongValue;
	}

}
