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
public class UnsignedByteOperand extends Operand {

	/**
	 * 
	 */
	public UnsignedByteOperand() {
		type = CTypeTables.CUBYTE;
	}

	@Override
	public Operand copy() {
		return new UnsignedByteOperand().addName(getName());
	}

	@Override
	public String getTypeShortName() {
		return "ub";
	}


}
