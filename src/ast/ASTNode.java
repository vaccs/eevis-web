/**
 *
 */
package ast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.CException;
import util.CTypeTables;
import visitor.ASTVisitor;

/**
 * @author carr
 *
 */
public abstract class ASTNode {

	protected short nodeId;					/* node id */
    protected String label;				/* a label for the node holding constants */
    protected ASTNode parent;			/* the parent node */
    protected List<ASTNode> children;	/* the children of this node in the tree */
    protected int realType;			    /* the type of the expression due to type declarations */
    protected int convertedType;			/* the type of the expression due to type coercion */
    protected int promotedType;			/* the type due to promotion in an operation */
    protected int lineNumber;			/* The line number from the original source */
    protected List<Short> ilocLines;	/* The lines of iloc code corresponding to this AST node */
		protected boolean promoted = false;
		protected short rule = CTypeTables.NO_RULE;
		protected boolean parens = false;

    private static short nextId = 0;

    /**
     * Constructor for an ASTNode that initializes the children linked list
     */
    protected ASTNode() {
        children = new LinkedList<ASTNode>();
        ilocLines = new ArrayList<Short>();
        nodeId = nextId++;
    }

    public short getNodeId() {
			return nodeId;
		}

		public static void resetNextId() {
				nextId = 0;
		}

		public String getNodeIdString() {
				return Short.toString(nodeId);
		}

	/**
     * Get a child from the list of children of this node
     * @param i the index into the list of children
     * @return the i<sup>th</sup> child of this node
     */
    public ASTNode getChild(int i) {
        return children.get(i);
    }

    /**
     * Add a child to this node at the <i>end</i> of the list of children
     * @param node the ASTNode to add as a child
     */
    public void putChild(ASTNode node) {
    	node.setParent(this);
        children.add(node);
    }

    /**
     * Add a child to this node at the <i>front</i> of the list of children
     * @param node the ASTNode to add as a child
     */
    public void pushChild(ASTNode node) {
    	node.setParent(this);
        children.add(0, node);
    }

    /**
     * Update a particular child of this node
     * @param i the index of the node to update
     * @param node the new i<sup>th</sup> child of this node
     */
    public void setChild(int i, ASTNode node) {
    	node.setParent(this);
        children.set(i, node);
    }

    /**
     * Get the label for this node
     * @return the node label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label for this node
     * @param label the new node label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Set the label of this node and return this instance of an ASTNode following a builder pattern
     * @param label the new label for this node
     * @return this instance of the ASTNode
     */
    public ASTNode addLabel(String label) {
    		setLabel(label);
    		return this;
    }

    public ASTNode getParent() {
		return parent;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}

	/**
     * Add a child to the end of the list of children and return this instance of an ASTNode following a builder pattern
     * @param node the new child
     * @return this instance of an ASTNode
     */
    public ASTNode addChild(ASTNode node) {
    		putChild(node);
    		return this;
    }

    /**
     * Add a line number to this ASTNode
     * @param ln a source line number
     * @return this instance of an ASTNode
     */
    public ASTNode addLineNumber(int ln) {
    	lineNumber = ln;
    	return this;
    }

    /**
     * Get the list of children of this node
     * @return the list of children
     */
    public List<ASTNode> getChildren() {
    		return children;
    }

    /**
	 * @return the realType
	 */
	public int getRealType() {
		return realType;
	}

	/**
	 * @param realType the realType to set
	 */
	public void setRealType(int realType) {
		this.realType = realType;
	}

	/**
	 * @return the convertType
	 */
	public int getConvertedType() {
		return convertedType;
	}

	/**
	 * @param convertType the convertType to set
	 */
	public void setConvertedType(int convertedType, short rule) {
		this.convertedType = convertedType;
		this.rule = rule;
	}

	public short getRule() {
		return rule;
	}
	/**
	 * @param convertType the convertType to set
	 */
	public void setConvertedType(int convertedType) {
		this.convertedType = convertedType;
		this.rule = CTypeTables.NO_RULE;
	}

	/**
	 * @return the promotedType
	 */
	public int getPromotedType() {
		return promotedType;
	}

	public boolean getPromoted() {
		return promoted;
	}

	/**
	 * @param promotedType the promotedType to set
	 */
	public void setPromotedType(int promotedType) {
		this.promotedType = promotedType;
		if (promotedType != realType)
			promoted = true;
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void addIlocLine(short ilocLine) {
		ilocLines.add(ilocLine);
	}

	public short getIlocLine(int i) {
		return ilocLines.get(i);
	}

	public List<Short> getIlocLines() {
		return ilocLines;
	}

	public void setParens() {
		parens = true;
	}

	public boolean getParens() {
		return parens;
	}

	/**
     * This is the accept method for the visitor pattern. It has been modified
     * to return a value to allow the implementation of an interpreter. The
     * modified visitor pattern was used so that the interpreter is not spread
     * over all of the AST files as would be the case using an interpreter
     * pattern.
     *
     * @param visitor - a visitor for an AST
     * @return an Object
     * @throws PLp1Error TODO
     */
    public abstract Object accept(ASTVisitor visitor) throws CException;
}
