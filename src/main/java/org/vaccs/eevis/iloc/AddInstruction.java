/**
 * 
 */
package org.vaccs.eevis.iloc;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.visitor.IlocVisitor;

/**
 * @author carr
 *
 */
public class AddInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public AddInstruction() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}
	
}
