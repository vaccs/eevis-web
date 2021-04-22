/**
 * 
 */
package org.vaccs.eevis.iloc.operand;

/**
 * @author carr
 *
 */
public class OperandFactory {

	/**
	 * 
	 */
	public OperandFactory() {
	}
	
	public Operand makeOperand(String operandType) {
		Operand oper = null;
		
		switch(operandType) {
		case "ByteOperand":
			oper = new ByteOperand();
			break;
		case "IntOperand":
			oper = new IntOperand();
			break;
		case "LongOperand":
			oper = new LongOperand();
			break;
		case "ShortOperand":
			oper = new ShortOperand();
			break;
		case "UnsignedByteOperand":
			oper = new UnsignedByteOperand();
			break;
		case "UnsignedIntOperand":
			oper = new UnsignedIntOperand();
			break;
		case "UnsignedLongOperand":
			oper = new UnsignedLongOperand();
			break;
		case "UnsignedShortOperand":
			oper = new UnsignedShortOperand();
			break;
		}
		
		return oper;
	}

}
