/**
 * 
 */
package org.vaccs.eevis.iloc.shortcopy;

import org.vaccs.eevis.iloc.IlocInstruction;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class S2bInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public S2bInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
