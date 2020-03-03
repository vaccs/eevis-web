/**
 * 
 */
package iloc.operand;

import java.math.BigInteger;

import util.CException;
import util.CTypeTables;
import util.Memory;
import value.CValue;
import value.CValueFactory;
import value.IntValue;

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
