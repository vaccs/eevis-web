/**
 * 
 */
package iloc.operand;

import util.CException;
import util.CTypeTables;
import util.Memory;
import value.CValue;
import value.IntValue;
import value.ShortValue;

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
