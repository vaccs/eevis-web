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
public class Us2ubInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Us2ubInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
