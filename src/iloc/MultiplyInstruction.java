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
public class MultiplyInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public MultiplyInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
