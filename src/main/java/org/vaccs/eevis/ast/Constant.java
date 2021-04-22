/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

/**
 * This class represents a reference to a C integer constant
 * @author carr
 *
 */
public class Constant extends Expression {

	/**
	 * Create a Constant object
	 */
	public Constant() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}
	
	public void negateConstant() {
		if (label.startsWith("-", 0))
			label = label.substring(1, label.length());
		else
			label = "-" + label;
	}

}
