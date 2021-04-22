/**
 * 
 */
package org.vaccs.eevis.iloc;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class DivideInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public DivideInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
