/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represents a unary plus operation
 * @author carr
 *
 */
public class UnaryPlus extends UnaryExpression {

	/**
	 * Create a UnaryPlus object
	 */
	public UnaryPlus() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
