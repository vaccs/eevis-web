/**
 * 
 */
package iloc;

import iloc.bytecopy.B2bInstruction;
import iloc.bytecopy.B2iInstruction;
import iloc.bytecopy.B2lInstruction;
import iloc.bytecopy.B2sInstruction;
import iloc.bytecopy.B2ubInstruction;
import iloc.bytecopy.B2uiInstruction;
import iloc.bytecopy.B2ulInstruction;
import iloc.bytecopy.B2usInstruction;
import iloc.bytecopy.Ub2bInstruction;
import iloc.bytecopy.Ub2iInstruction;
import iloc.bytecopy.Ub2lInstruction;
import iloc.bytecopy.Ub2sInstruction;
import iloc.bytecopy.Ub2ubInstruction;
import iloc.bytecopy.Ub2uiInstruction;
import iloc.bytecopy.Ub2ulInstruction;
import iloc.bytecopy.Ub2usInstruction;
import iloc.intcopy.I2bInstruction;
import iloc.intcopy.I2iInstruction;
import iloc.intcopy.I2lInstruction;
import iloc.intcopy.I2sInstruction;
import iloc.intcopy.I2ubInstruction;
import iloc.intcopy.I2uiInstruction;
import iloc.intcopy.I2ulInstruction;
import iloc.intcopy.I2usInstruction;
import iloc.intcopy.Ui2bInstruction;
import iloc.intcopy.Ui2iInstruction;
import iloc.intcopy.Ui2lInstruction;
import iloc.intcopy.Ui2sInstruction;
import iloc.intcopy.Ui2ubInstruction;
import iloc.intcopy.Ui2uiInstruction;
import iloc.intcopy.Ui2ulInstruction;
import iloc.intcopy.Ui2usInstruction;
import iloc.longcopy.L2bInstruction;
import iloc.longcopy.L2iInstruction;
import iloc.longcopy.L2lInstruction;
import iloc.longcopy.L2sInstruction;
import iloc.longcopy.L2ubInstruction;
import iloc.longcopy.L2uiInstruction;
import iloc.longcopy.L2ulInstruction;
import iloc.longcopy.L2usInstruction;
import iloc.longcopy.Ul2bInstruction;
import iloc.longcopy.Ul2iInstruction;
import iloc.longcopy.Ul2lInstruction;
import iloc.longcopy.Ul2sInstruction;
import iloc.longcopy.Ul2ubInstruction;
import iloc.longcopy.Ul2uiInstruction;
import iloc.longcopy.Ul2ulInstruction;
import iloc.longcopy.Ul2usInstruction;
import iloc.shortcopy.S2bInstruction;
import iloc.shortcopy.S2iInstruction;
import iloc.shortcopy.S2lInstruction;
import iloc.shortcopy.S2sInstruction;
import iloc.shortcopy.S2ubInstruction;
import iloc.shortcopy.S2uiInstruction;
import iloc.shortcopy.S2ulInstruction;
import iloc.shortcopy.S2usInstruction;
import iloc.shortcopy.Us2bInstruction;
import iloc.shortcopy.Us2iInstruction;
import iloc.shortcopy.Us2lInstruction;
import iloc.shortcopy.Us2sInstruction;
import iloc.shortcopy.Us2ubInstruction;
import iloc.shortcopy.Us2uiInstruction;
import iloc.shortcopy.Us2ulInstruction;
import iloc.shortcopy.Us2usInstruction;
import util.CException;

/**
 * @author carr
 *
 */
public class InstructionFactory {

	/**
	 * 
	 */
	public InstructionFactory() {
	}

	public IlocInstruction makeInstruction(String instructionType) throws CException {
		IlocInstruction inst = null;
		
		switch (instructionType) {
		case "AddInstruction":
			inst = new AddInstruction();
			break;
		case "B2bInstruction":
			inst = new B2bInstruction();
			break;
		case "B2iInstruction":
			inst = new B2iInstruction();
			break;
		case "B2lInstruction":
			inst = new B2lInstruction();
			break;
		case "B2sInstruction":
			inst = new B2sInstruction();
			break;
		case "B2ubInstruction":
			inst = new B2ubInstruction();
			break;
		case "B2uiInstruction":
			inst = new B2uiInstruction();
			break;
		case "B2ulInstruction":
			inst = new B2ulInstruction();
			break;
		case "B2usInstruction":
			inst = new B2usInstruction();
			break;
		case "DivideInstruction":
			inst = new DivideInstruction();
			break;
		case "I2bInstruction":
			inst = new I2bInstruction();
			break;
		case "I2iInstruction":
			inst = new I2iInstruction();
			break;
		case "I2lInstruction":
			inst = new I2lInstruction();
			break;
		case "I2sInstruction":
			inst = new I2sInstruction();
			break;
		case "I2ubInstruction":
			inst = new I2ubInstruction();
			break;
		case "I2uiInstruction":
			inst = new I2uiInstruction();
			break;
		case "I2ulInstruction":
			inst = new I2ulInstruction();
			break;
		case "I2usInstruction":
			inst = new I2usInstruction();
			break;
		case "L2bInstruction":
			inst = new L2bInstruction();
			break;
		case "L2iInstruction":
			inst = new L2iInstruction();
			break;
		case "L2lInstruction":
			inst = new L2lInstruction();
			break;
		case "L2sInstruction":
			inst = new L2sInstruction();
			break;
		case "L2ubInstruction":
			inst = new L2ubInstruction();
			break;
		case "L2uiInstruction":
			inst = new L2uiInstruction();
			break;
		case "L2ulInstruction":
			inst = new L2ulInstruction();
			break;
		case "L2usInstruction":
			inst = new L2usInstruction();
			break;
		case "LoadIInstruction":
			inst = new LoadIInstruction();
			break;
		case "MultiplyInstruction":
			inst = new MultiplyInstruction();
			break;
		case "OutputInstruction":
			inst = new OutputInstruction();
			break;
		case "S2bInstruction":
			inst = new S2bInstruction();
			break;
		case "S2iInstruction":
			inst = new S2iInstruction();
			break;
		case "S2lInstruction":
			inst = new S2lInstruction();
			break;
		case "S2sInstruction":
			inst = new S2sInstruction();
			break;
		case "S2ubInstruction":
			inst = new S2ubInstruction();
			break;
		case "S2uiInstruction":
			inst = new S2uiInstruction();
			break;
		case "S2ulInstruction":
			inst = new S2ulInstruction();
			break;
		case "S2usInstruction":
			inst = new S2usInstruction();
			break;
		case "SubtractInstruction":
			inst = new SubtractInstruction();
			break;
		case "Ub2bInstruction":
			inst = new Ub2bInstruction();
			break;
		case "Ub2iInstruction":
			inst = new Ub2iInstruction();
			break;
		case "Ub2lInstruction":
			inst = new Ub2lInstruction();
			break;
		case "Ub2sInstruction":
			inst = new Ub2sInstruction();
			break;
		case "Ub2ubInstruction":
			inst = new Ub2ubInstruction();
			break;
		case "Ub2uiInstruction":
			inst = new Ub2uiInstruction();
			break;
		case "Ub2ulInstruction":
			inst = new Ub2ulInstruction();
			break;
		case "Ub2usInstruction":
			inst = new Ub2usInstruction();
			break;
		case "Ui2bInstruction":
			inst = new Ui2bInstruction();
			break;
		case "Ui2iInstruction":
			inst = new Ui2iInstruction();
			break;
		case "Ui2lInstruction":
			inst = new Ui2lInstruction();
			break;
		case "Ui2sInstruction":
			inst = new Ui2sInstruction();
			break;
		case "Ui2ubInstruction":
			inst = new Ui2ubInstruction();
			break;
		case "Ui2uiInstruction":
			inst = new Ui2uiInstruction();
			break;
		case "Ui2ulInstruction":
			inst = new Ui2ulInstruction();
			break;
		case "Ui2usInstruction":
			inst = new Ui2usInstruction();
			break;
		case "Ul2bInstruction":
			inst = new Ul2bInstruction();
			break;
		case "Ul2iInstruction":
			inst = new Ul2iInstruction();
			break;
		case "Ul2lInstruction":
			inst = new Ul2lInstruction();
			break;
		case "Ul2sInstruction":
			inst = new Ul2sInstruction();
			break;
		case "Ul2ubInstruction":
			inst = new Ul2ubInstruction();
			break;
		case "Ul2uiInstruction":
			inst = new Ul2uiInstruction();
			break;
		case "Ul2ulInstruction":
			inst = new Ul2ulInstruction();
			break;
		case "Ul2usInstruction":
			inst = new Ul2usInstruction();
			break;
		case "Us2bInstruction":
			inst = new Us2bInstruction();
			break;
		case "Us2iInstruction":
			inst = new Us2iInstruction();
			break;
		case "Us2lInstruction":
			inst = new Us2lInstruction();
			break;
		case "Us2sInstruction":
			inst = new Us2sInstruction();
			break;
		case "Us2ubInstruction":
			inst = new Us2ubInstruction();
			break;
		case "Us2uiInstruction":
			inst = new Us2uiInstruction();
			break;
		case "Us2ulInstruction":
			inst = new Us2ulInstruction();
			break;
		case "Us2usInstruction":
			inst = new Us2usInstruction();
			break;
		default:
			throw new CException("Invalid instruction factory request: " + instructionType);
		}
		
		return inst;
	}
}
