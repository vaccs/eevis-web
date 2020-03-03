/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represents an assignment statement 
 * @author carr
 *
 */
public class Assignment extends Statement {

	/**
	 * Create an instance of an Assignment
	 */
	public Assignment() {
	}
	
	/**
	 * Get the l-value of the assignment. This must be an identifier
	 * @return an IdDef that represents the l-value of an assignment statement
	 */
	public IdDef getLhs() {
		return (IdDef)getChild(0);
	}
	
	/**
	 * Get the r-value of an assignment
	 * @return an Expression that is the r-value of an assignment statement
	 */
	public Expression getRhs() {
		return (Expression)getChild(1);
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
