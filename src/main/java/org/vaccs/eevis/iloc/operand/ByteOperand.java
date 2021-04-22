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
public class ByteOperand extends Operand {

	/**
	 * 
	 */
	public ByteOperand() {
		type = CTypeTables.CBYTE;
	}

	@Override
	public Operand copy() {
		return new ByteOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "b";
	}

}
