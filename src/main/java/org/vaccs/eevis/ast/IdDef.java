/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * A class representing an identifier appearing on the left-hand side of an assignment statement
 * @author carr
 *
 */
public class IdDef extends ASTNode {

	/**
	 * Create an IdDecl node
	 */
	public IdDef() {
	}

	/**
	 * Get the string representation of the identifier being defined
	 * @return the id represented by this node
	 */
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
