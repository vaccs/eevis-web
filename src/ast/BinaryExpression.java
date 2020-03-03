/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This abstract class is the super class for all binary expressions
 * @author carr
 *
 */
public abstract class BinaryExpression extends Expression {

	/**
	 * get the left operand of a binary expression
	 * @return the left operand of this expression
	 */
	public Expression getLeftOperand() {
		return (Expression)getChild(0);
	}

	/**
	 * get the right operand of a binary expression
	 * @return the right operand of this expression
	 */
	public Expression getRightOperand() {
		return (Expression)getChild(1);
	}

}
