/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represents a C divide expression
 * @author carr
 *
 */
public class Divide extends BinaryExpression {

	/**
	 * Create a Divide object
	 */
	public Divide() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
