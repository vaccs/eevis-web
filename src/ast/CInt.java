/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A C int type specification
 * @author carr
 *
 */
public class CInt extends CTypeName {

	/**
	 * Create a CInt node
	 */
	public CInt() {
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
		return "int";
	}

}
