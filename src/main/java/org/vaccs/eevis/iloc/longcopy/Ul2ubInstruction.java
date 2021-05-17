/**
 * 
 */
package org.vaccs.eevis.iloc.longcopy;

import org.vaccs.eevis.iloc.IlocInstruction;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class Ul2ubInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public Ul2ubInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
