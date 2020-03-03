/**
 * 
 */
package ast;

import java.util.List;

import util.CException;
import visitor.ASTVisitor;

/**
 * A class representing a variable declaration that involves initialization by a constant
 * @author carr
 *
 */
public class InitializeDecl extends CDeclaration {

	/**
	 * Create an InitializeDecl node
	 */
	public InitializeDecl() {
	}
	
	/**
	 * Get the id being declared
	 * @return the string representation of the id being declared
	 */
	public String getId() {
		return getChild(0).getLabel();
	}
	
	public ASTNode getIdNode() {
		return getChild(0);
	}
	
	/**
	 * Get the initial value assigned to the id
	 * @return the string representation of the initial value
	 */
	public String getInitialValue() {
		return getChild(1).getLabel();
	}
	
	public ASTNode getInitialValueNode() {
		return getChild(1);
	}
	
	public List<Short> getIlocLines() {
		return getChild(1).getIlocLines();
	}


	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
