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
public class I2iInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public I2iInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
