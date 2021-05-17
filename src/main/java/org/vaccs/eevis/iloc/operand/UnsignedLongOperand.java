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
public class UnsignedLongOperand extends Operand {

	/**
	 * 
	 */
	public UnsignedLongOperand() {
		type = CTypeTables.CULONG;
	}

	@Override
	public Operand copy() {
		return new UnsignedLongOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "ul";
	}

}
