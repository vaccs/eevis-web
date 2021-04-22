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
public class UnsignedIntOperand extends Operand {

	/**
	 * 
	 */
	public UnsignedIntOperand() {
		type = CTypeTables.CUINT;
	}

	@Override
	public Operand copy() {
		return new UnsignedIntOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "ui";
	}

}
