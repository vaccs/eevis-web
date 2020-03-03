/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A class for an id appearing in a type declaration statement
 * @author carr
 *
 */
public class IdDecl extends CDeclaration {

	/**
	 * Create an IdDecl node
	 */
	public IdDecl() {
	}
	
	/**
	 * Get the name of the id associated with this node
	 * @return the string representation of the id being declared
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
