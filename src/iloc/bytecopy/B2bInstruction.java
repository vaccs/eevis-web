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
public class B2bInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public B2bInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
