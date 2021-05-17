/**
 * 
 */
package org.vaccs.eevis.ast;

/**
 * @author carr
 *
 */
public class ASTNodeFactory {

	/**
	 * A factory for creating ASTNodes
	 */
	public ASTNodeFactory() {
	}

	/**
	 * Create a new ASTNode object of a specific sub-class
	 * @param nodeType a string class name of the object to be created
	 * @return the new ASTNode
	 */
	public ASTNode makeASTNode(String nodeType) {
		ASTNode node = null;
		
		if (nodeType.equals("Add"))
			node = new Add();
		else if (nodeType.equals("Assignment"))
			node = new Assignment();
		else if (nodeType.equals("CByte"))
			node = new CByte();
		else if (nodeType.equals("CInt"))
			node = new CInt();
		else if (nodeType.equals("CLong"))
			node = new CLong();
		else if (nodeType.equals("Constant"))
			node = new Constant();
		else if (nodeType.equals("CShort"))
			node = new CShort();
		else if (nodeType.equals("Divide"))
			node = new Divide();
		else if (nodeType.equals("IdDecl"))
			node = new IdDecl();
		else if (nodeType.equals("IdDef"))
			node = new IdDef();
		else if (nodeType.equals("IdRef"))
			node = new IdRef();
		else if (nodeType.equals("InitializeDecl"))
			node = new InitializeDecl();
		else if (nodeType.equals("Multiply"))
			node = new Multiply();
		else if (nodeType.equals("Program"))
			node = new Program();
		else if (nodeType.equals("Put"))
			node = new Put();
		else if (nodeType.equals("Signed"))
			node = new Signed();
		else if (nodeType.equals("Subtract"))
			node = new Subtract();
		else if (nodeType.equals("TypeCast"))
			node = new TypeCast();
		else if (nodeType.equals("TypeSpecification"))
			node = new TypeSpecification();
		else if (nodeType.equals("UnaryMinus"))
			node = new UnaryMinus();
		else if (nodeType.equals("UnaryPlus"))
			node = new UnaryPlus();
		else if (nodeType.equals("Unsigned"))
			node = new Unsigned();
		else if (nodeType.equals("VariableDeclaration"))
			node = new VariableDeclaration();
		else
			throw new RuntimeException("Invalid AST node type "+nodeType+" in makeASTNode()");
		
		return node;
	}

}
