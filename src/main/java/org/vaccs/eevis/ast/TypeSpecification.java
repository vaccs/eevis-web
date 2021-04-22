/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * A class representing a C type specification
 * @author carr
 *
 */
public class TypeSpecification extends Statement {

	/**
	 * Create a TypeSpecification node
	 */
	public TypeSpecification() {
	}
	
	/**
	 * Determine if this represents a signed specification
	 * @return true if this specification is a signed type
	 */
	public boolean isSigned() {
		return getChild(0) instanceof Signed;
	}
	
	/**
	 * Determine if this represents an unsigned specification
	 * @return true if this specification is an unsigned type
	 */
	public boolean isUnsigned() {
		return getChild(0) instanceof Unsigned;
	}

	/**
	 * Get type C type for this specification
	 * @return the node representing the C type of this specification
	 */
	public CTypeName getType() {
		return (CTypeName) getChild(1);
	}
	
	public ASTNode getSignNode() {
		return getChild(0);
	}
	
	
	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
