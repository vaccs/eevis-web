/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A class representing a signed type specification
 * @author carr
 *
 */
public class Signed extends ASTNode {

	/**
	 * Create a Signed node
	 */
	public Signed() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
