/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A C short type specification ode
 * @author carr
 *
 */
public class CShort extends CTypeName {

	/**
	 * Create a CShort object
	 */
	public CShort() {
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

	@Override
	public String getTypeName() {
		return "short";
	}

}
