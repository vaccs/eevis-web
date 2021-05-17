/**
 * 
 */
package org.vaccs.eevis.iloc.operand;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.value.IntValue;
import org.vaccs.eevis.value.ShortValue;

/**
 * @author carr
 *
 */
public class ShortOperand extends Operand {

	/**
	 * 
	 */
	public ShortOperand() {
		type = CTypeTables.CSHORT;
	}

	@Override
	public Operand copy() {
		Operand op = opFactory.makeOperand("ShortOperand").addName(getName());
		if (isConstantValue)
			return op.addIsConstant();
		else 
			return op;
	}

	@Override
	public String getTypeShortName() {
		return "s";
	}

}
