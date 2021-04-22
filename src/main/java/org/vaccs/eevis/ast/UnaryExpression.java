/**
 * 
 */
package org.vaccs.eevis.ast;

/**
 * @author carr
 *
 */
public abstract class UnaryExpression extends Expression {

	
	/**
	 * Get the operand for a unary expression
	 * @return the operand
	 */
	public Expression getOperand() {
		return (Expression) getChild(0);
	}
}
