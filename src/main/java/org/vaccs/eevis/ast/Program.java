/**
 * 
 */
package org.vaccs.eevis.ast;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.visitor.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a set of C declarations and assignment statements
 * @author carr
 *
 */
public class Program extends ASTNode {
	
	List<Statement> statements = null;

	/**
	 * Create a Program node
	 */
	public Program() {
	}
	
	public List<Statement> getStatements() {
		
		if (statements == null)
			statements = computeStatementList();
		
		return statements;
			
	}
	/**
	 * Compute the list of statements for this program
	 * @return a list of Statement nodes representing a program
	 */
	protected List<Statement> computeStatementList() {
		List<Statement> statements = new LinkedList<Statement>();
		
		getChildren().forEach(child -> statements.add((Statement)child));
		
		return statements;
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#accept(visitor.Visitor)
	 */
	@Override
	public Object accept(ASTVisitor visitor) throws CException {
		return visitor.visit(this);
	}

}
