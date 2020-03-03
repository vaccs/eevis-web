/**
 * 
 */
package visitor;

import ast.*;
import util.CException;

/**
 * @author carr
 *
 */
public interface ASTVisitor<T> {
	
	/**
	 * Visit an Add node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Add n) throws CException;

	/**
	 * Visit an Assignment node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Assignment n) throws CException;
	
	/**
	 * Visit an CByte node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(CByte n) throws CException;
	
	/**
	 * Visit an CInt node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(CInt n) throws CException;
	
	/**
	 * Visit an CLong node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(CLong n) throws CException;
	
	/**
	 * Visit an Constant node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Constant n) throws CException;
	
	/**
	 * Visit an CShort node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(CShort n) throws CException;
		
	/**
	 * Visit an Divide node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Divide n) throws CException;
	
	/**
	 * Visit an IdDecl node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(IdDecl n) throws CException;
	
	/**
	 * Visit an IdDef node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(IdDef n) throws CException;
	
	/**
	 * Visit an IdRef node 
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(IdRef n) throws CException;
	
	/**
	 * Visit an InitializeDecl node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(InitializeDecl n) throws CException;
	
	/**
	 * Visit an Multiply node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Multiply n) throws CException;
	
	/**
	 * Visit an Program node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Program n) throws CException;
	
	/**
	 * Visit an Subtract node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Subtract n) throws CException;
	
	/**
	 * Visit an Signed node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Signed n) throws CException;
	
	/**
	 * Visit an TypeCast node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(TypeCast n) throws CException;
	
	/**
	 * Visit an TypeSpecification node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(TypeSpecification n) throws CException;
	
	/**
	 * Visit an UnaryMinus node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(UnaryMinus n) throws CException;
	
	/**
	 * Visit an UnaryPlus node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(UnaryPlus n) throws CException;
	
	/**
	 * Visit an Unsigned node
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(Unsigned n) throws CException;
	
	/**
	 * Visit an VariableDeclaration node and its children
	 * @param n an Add node
	 * @return a value of type T
	 * @throws CException
	 */
	public T visit(VariableDeclaration n) throws CException;

	public T visit(Put put) throws CException;

}
