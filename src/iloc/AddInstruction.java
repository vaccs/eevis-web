/**
 * 
 */
package iloc;

import util.CException;
import util.Memory;
import value.CValue;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class AddInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public AddInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}
	
}
