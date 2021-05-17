/**
 * 
 */
package org.vaccs.eevis.iloc.bytecopy;

import org.vaccs.eevis.iloc.IlocInstruction;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class B2ubInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public B2ubInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
