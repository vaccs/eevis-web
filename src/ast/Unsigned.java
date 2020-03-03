/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A class representing an unsigned type specification
 * @author carr
 *
 */
public class Unsigned extends ASTNode {

	/**
	 * Create an Unsigned object
	 */
	public Unsigned() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
