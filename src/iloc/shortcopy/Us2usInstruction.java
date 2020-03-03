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
public class Us2usInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Us2usInstruction() {
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
