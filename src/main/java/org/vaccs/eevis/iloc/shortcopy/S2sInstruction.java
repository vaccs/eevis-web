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
public class S2sInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public S2sInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
