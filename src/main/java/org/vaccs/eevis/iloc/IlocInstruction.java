/**
 *
 */
package org.vaccs.eevis.iloc;

import org.vaccs.eevis.ast.ASTNode;
import org.vaccs.eevis.iloc.operand.Operand;
import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.visitor.IlocVisitor;

import java.util.List;
import java.util.ArrayList;
/**
 * @author carr
 *
 */
public abstract class IlocInstruction {

	private Operand src1;
	private Operand src2;
	private Operand dest;
	private ASTNode node;
	private short ilocId;
	private short conversionRule = CTypeTables.NO_RULE;
	private boolean promotion = false;
	private boolean initialization = false;

	private static short nextId = 0;
	/**
	 * constructor for an IlocInstruction
	 */
	public IlocInstruction() {
		ilocId = nextId++;
	}

	public static void resetNextId() {
		nextId = 0;
	}

	/**
	 * add the first source operand to the instruction
	 * @param oper the first source operand
	 * @return the instruction
	 */
	public IlocInstruction addSrc1(Operand oper) {
		src1 = oper;
		return this;
	}

	/**
	 * add the second source operand to the instruction
	 * @param oper the second source operand
	 * @return the instruction
	 */
	public IlocInstruction addSrc2(Operand oper) {
		src2 = oper;
		return this;
	}

	/**
	 * add the destination operand to the instruction
	 * @param oper the destination operand
	 * @return the instruction
	 */
	public IlocInstruction addDest(Operand oper) {
		dest = oper;
		return this;
	}

	public IlocInstruction addASTNode(ASTNode n) {
		node = n;
		return this;
	}

	public IlocInstruction addPromotion() {
		this.promotion = true;
		return this;
	}

	public IlocInstruction addConversionRule(short n) {
		this.conversionRule = n;
		return this;
	}


	public IlocInstruction addInitialization() {
		this.initialization = true;
		return this;
	}
	/**
	 * @return the first source operand
	 */
	public Operand getSrc1() {
		return src1;
	}

	/**
	 * @return the second source operand
	 */
	public Operand getSrc2() {
		return src2;
	}

	/**
	 * @return the destination operand
	 */
	public Operand getDest() {
		return dest;
	}

	public ASTNode getASTNode() {
		return node;
	}

	public short getIlocId() {
		return ilocId;
	}

	public short getAstId() {
		return node.getNodeId();
	}

	public short getConversionRule() {
		return conversionRule;
	}

	public boolean getPromotion() {
		return promotion;
	}

	public int getDestType() {
		if (dest == null)
			return CTypeTables.NOTYPE;
		else
			return dest.getType();
	}

	public List<Integer> getSrcTypes() {
		List<Integer> srcTypes = new ArrayList<Integer>();

		if (src1 != null)
			srcTypes.add(src1.getType());

		if (src2 != null)
			srcTypes.add(src2.getType());

		return srcTypes;
	}

	public boolean getInitialization() {
		return initialization;
	}

	@SuppressWarnings("rawtypes")
	public abstract Object accept(IlocVisitor visitor) throws CException ;

}
