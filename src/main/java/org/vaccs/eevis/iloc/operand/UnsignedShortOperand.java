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
public class UnsignedShortOperand extends Operand {

	/**
	 * 
	 */
	public UnsignedShortOperand() {
		type = CTypeTables.CUSHORT;
	}

	@Override
	public Operand copy() {
		return new UnsignedShortOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "us";
	}

}
