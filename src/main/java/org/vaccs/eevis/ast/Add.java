/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * This class represents an add expression
 * @author carr
 *
 */
public class Add extends BinaryExpression {

	/**
	 * Create an Add object
	 */
	public Add() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
