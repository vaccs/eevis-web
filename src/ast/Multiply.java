/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represents a C multiply operation
 * @author carr
 *
 */
public class Multiply extends BinaryExpression {

	/**
	 * Create a Multiply node
	 */
	public Multiply() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
