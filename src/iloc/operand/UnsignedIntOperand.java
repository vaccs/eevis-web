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
