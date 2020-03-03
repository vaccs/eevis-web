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
public class Ul2ulInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Ul2ulInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
