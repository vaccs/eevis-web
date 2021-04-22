/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * This class represents a C subtract expression
 * @author carr
 *
 */
public class Subtract extends BinaryExpression {

	/**
	 * Create a Subtract object
	 */
	public Subtract() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
