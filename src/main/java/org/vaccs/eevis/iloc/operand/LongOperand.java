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
public class LongOperand extends Operand {

	/**
	 * 
	 */
	public LongOperand() {
		type = CTypeTables.CLONG;
	}

	@Override
	public Operand copy() {
		return new LongOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "l";
	}


}
