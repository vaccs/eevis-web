/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * A C char type specification node
 * @author carr
 *
 */
public class CByte extends CTypeName {

	/**
	 * Create a new CByte object
	 */
	public CByte() {
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
		return "char";
	}

}
