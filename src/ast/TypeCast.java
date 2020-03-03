/**
 * 
 */
package ast;

import util.CException;
import visitor.ASTVisitor;

/**
 * This class represent a C type cast
 * @author carr
 *
 */
public class TypeCast extends Expression {

	/**
	 * Create a TypeCast object
	 */
	public TypeCast() {
	}

	/**
	 * Get the type name of this cast
	 * @return the type of the cast
	 */
	public TypeSpecification getCastType() {
		return (TypeSpecification)getChild(0);
	}
	
	/**
	 * Get the expression to which the cast is applied
	 * @return the type casted expression
	 */
	public Expression getExpression() {
		return (Expression) getChild(1);
	}
	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
