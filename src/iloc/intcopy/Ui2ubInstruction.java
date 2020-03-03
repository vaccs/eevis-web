/**
 * 
 */
package iloc.intcopy;

import iloc.IlocInstruction;
import util.CException;
import visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class Ui2ubInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Ui2ubInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
