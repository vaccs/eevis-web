/**
 * 
 */
package org.vaccs.eevis.iloc.operand;

import java.math.BigInteger;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.value.CValueFactory;
import org.vaccs.eevis.value.IntValue;

/**
 * @author carr
 *
 */
public class IntOperand extends Operand {

	/**
	 * 
	 */
	public IntOperand() {
		type = CTypeTables.CINT;
	}

	@Override
	public Operand copy() {
		Operand op = opFactory.makeOperand("IntOperand").addName(getName());
		if (isConstantValue)
			return op.addIsConstant();
		else 
			return op;
	}

	@Override
	public String getTypeShortName() {
		return "i";
	}


}
