/**
 *
 */
package org.vaccs.eevis.visitor;

import java.io.IOException;

import org.vaccs.eevis.iloc.AddInstruction;
import org.vaccs.eevis.iloc.DivideInstruction;
import org.vaccs.eevis.iloc.LoadIInstruction;
import org.vaccs.eevis.iloc.MultiplyInstruction;
import org.vaccs.eevis.iloc.OutputInstruction;
import org.vaccs.eevis.iloc.SubtractInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2bInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2iInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2lInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2sInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2ubInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2uiInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2ulInstruction;
import org.vaccs.eevis.iloc.bytecopy.B2usInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2bInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2iInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2lInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2sInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2ubInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2uiInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2ulInstruction;
import org.vaccs.eevis.iloc.bytecopy.Ub2usInstruction;
import org.vaccs.eevis.iloc.intcopy.I2bInstruction;
import org.vaccs.eevis.iloc.intcopy.I2iInstruction;
import org.vaccs.eevis.iloc.intcopy.I2lInstruction;
import org.vaccs.eevis.iloc.intcopy.I2sInstruction;
import org.vaccs.eevis.iloc.intcopy.I2ubInstruction;
import org.vaccs.eevis.iloc.intcopy.I2uiInstruction;
import org.vaccs.eevis.iloc.intcopy.I2ulInstruction;
import org.vaccs.eevis.iloc.intcopy.I2usInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2bInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2iInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2lInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2sInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2ubInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2uiInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2ulInstruction;
import org.vaccs.eevis.iloc.intcopy.Ui2usInstruction;
import org.vaccs.eevis.iloc.longcopy.L2bInstruction;
import org.vaccs.eevis.iloc.longcopy.L2iInstruction;
import org.vaccs.eevis.iloc.longcopy.L2lInstruction;
import org.vaccs.eevis.iloc.longcopy.L2sInstruction;
import org.vaccs.eevis.iloc.longcopy.L2ubInstruction;
import org.vaccs.eevis.iloc.longcopy.L2uiInstruction;
import org.vaccs.eevis.iloc.longcopy.L2ulInstruction;
import org.vaccs.eevis.iloc.longcopy.L2usInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2bInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2iInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2lInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2sInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2ubInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2uiInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2ulInstruction;
import org.vaccs.eevis.iloc.longcopy.Ul2usInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2bInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2iInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2lInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2sInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2ubInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2uiInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2ulInstruction;
import org.vaccs.eevis.iloc.shortcopy.S2usInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2bInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2iInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2lInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2sInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2ubInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2uiInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2ulInstruction;
import org.vaccs.eevis.iloc.shortcopy.Us2usInstruction;
import org.vaccs.eevis.util.CException;

/**
 * @author carr
 *
 */
public class IlocSourceVisitor implements IlocVisitor<String> {

	/**
	 *
	 */
	public IlocSourceVisitor() throws CException {
	}

	@Override
	public String visit(AddInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName() + " + "
				+ inst.getSrc2().getName();
	}

	@Override
	public String visit(B2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(B2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(DivideInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName() + " / "
				+ inst.getSrc2().getName();
	}

	@Override
	public String visit(I2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(I2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(L2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(MultiplyInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName() + " * "
				+ inst.getSrc2().getName();
	}

	@Override
	public String visit(S2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(S2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(SubtractInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName() + " - "
				+ inst.getSrc2().getName();
	}

	@Override
	public String visit(Ub2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ub2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ui2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Ul2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2bInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2iInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2lInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2sInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2ubInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2uiInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2ulInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(Us2usInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+ inst.getSrc1().getName();
	}

	@Override
	public String visit(OutputInstruction inst) throws CException {
		return "output " + inst.getSrc1().getName();
	}

	@Override
	public String visit(LoadIInstruction inst) throws CException {
		return inst.getDest().getName() + " = "
				+inst.getSrc1().getName();
	}

}
