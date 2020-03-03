/**
 * 
 */
package iloc.longcopy;

import iloc.IlocInstruction;
import util.CException;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class Ul2uiInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Ul2uiInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
