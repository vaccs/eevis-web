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
