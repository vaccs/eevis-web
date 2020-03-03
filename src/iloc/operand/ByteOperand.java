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
