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
public class L2bInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public L2bInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
