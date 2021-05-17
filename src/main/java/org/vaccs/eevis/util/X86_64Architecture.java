/**
 * 
 */
package org.vaccs.eevis.util;

import java.math.BigInteger;

/**
 * @author carr
 *
 */
public class X86_64Architecture extends ArchitectureType {

	public X86_64Architecture() {
		minLongValue =  new BigInteger("-9223372036854775808");
		maxLongValue =  new BigInteger("9223372036854775807");
		maxUnsignedLongValue = new BigInteger("18446744073709551615");
	}

	@Override
	public int longSize() {
		return 8;
	}

}
