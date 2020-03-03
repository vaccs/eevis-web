/**
 * 
 */
package iloc.shortcopy;

import iloc.IlocInstruction;
import util.CException;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class S2usInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public S2usInstruction() {
	}

	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#accept(visitor.IlocVisitor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
