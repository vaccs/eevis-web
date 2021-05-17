/**
 * 
 */
package org.vaccs.eevis.iloc.operand;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;

/**
 * @author carr
 *
 */
public class UnsignedByteOperand extends Operand {

	/**
	 * 
	 */
	public UnsignedByteOperand() {
		type = CTypeTables.CUBYTE;
	}

	@Override
	public Operand copy() {
		return new UnsignedByteOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "ub";
	}


}
