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
