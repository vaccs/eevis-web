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
public class DivideInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public DivideInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
