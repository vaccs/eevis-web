/**
 * 
 */
package util;

import java.math.BigInteger;

/**
 * @author carr
 *
 */
public class IA32Architecture extends ArchitectureType {

	public IA32Architecture() {
		minLongValue =  new BigInteger("-2147483648");
		maxLongValue =  new BigInteger("2147483647");
		maxUnsignedLongValue = new BigInteger("4294967295");
	}

	@Override
	public int longSize() {
		return 4;
	}

}
