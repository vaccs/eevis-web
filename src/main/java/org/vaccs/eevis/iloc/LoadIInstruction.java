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
public class LoadIInstruction extends IlocInstruction {

	/**
	 * 
	 */
	public LoadIInstruction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#accept(visitor.IlocVisitor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(IlocVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
