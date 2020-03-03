/**
 * 
 */
package iloc.bytecopy;

import iloc.IlocInstruction;
import util.CException;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class B2uiInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public B2uiInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
