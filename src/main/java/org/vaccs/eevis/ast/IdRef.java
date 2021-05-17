/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * This class represents a C reference to an identifier
 * @author carr
 *
 */
public class IdRef extends Expression {

	/**
	 * Create an IdRef object
	 */
	public IdRef() {
	}

	public String getId() {
		return getLabel();
	}
	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
