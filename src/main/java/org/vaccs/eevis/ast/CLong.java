/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * A C long type specification node
 * @author carr
 *
 */
public class CLong extends CTypeName {

	/**
	 * Create a CLong object
	 */
	public CLong() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

	@Override
	public String getTypeName() {
		return "long";
	}

}
