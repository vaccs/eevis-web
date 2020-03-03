/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * @author carr
 *
 */
public class Put extends Statement {

	/**
	 * 
	 */
	public Put() {
	}
	
	public ASTNode getId() {
		return getChild(0);
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.ASTVisitor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
