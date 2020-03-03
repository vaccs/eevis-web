/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represents a unary minus operation
 * @author carr
 *
 */
public class UnaryMinus extends UnaryExpression {

	/**
	 * Create a UnaryMinus object
	 */
	public UnaryMinus() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
