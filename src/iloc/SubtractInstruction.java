/**
 * 
 */
package iloc;

import util.CException;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class SubtractInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public SubtractInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
