/**
 * 
 */
package ast;

import java.util.List;
import java.util.ArrayList;

import util.CException;
import visitor.ASTVisitor;

/**
 * A class to represent a variable declaration
 * @author carr
 *
 */
public class VariableDeclaration extends Statement {

	/**
	 * Create a VariableDeclaration object
	 */
	public VariableDeclaration() {
		// TODO Auto-generated constructor stub
	}

	public TypeSpecification getTypeSpecification() {
		return (TypeSpecification)getChild(0);
	}
	
	public List<ASTNode> getDecls() {
		return getChildren().subList(1, getChildren().size());
	}
	
	public List<Short> getIlocLines() {
		List<Short> ilocList = new ArrayList<Short>();
		for (ASTNode child : getDecls())
			ilocList.addAll(child.getIlocLines());
		return ilocList;
	}

	
	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
