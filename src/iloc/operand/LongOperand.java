/**
 * 
 */
package iloc.operand;

import util.CException;
import util.CTypeTables;
import util.Memory;
import value.CValue;

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
