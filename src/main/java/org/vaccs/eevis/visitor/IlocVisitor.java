/**
 * 
 */
package org.vaccs.eevis.visitor;

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
public interface IlocVisitor<T> {

	T visit(AddInstruction inst) throws CException;

	T visit(B2bInstruction inst) throws CException;

	T visit(B2iInstruction inst) throws CException;

	T visit(B2lInstruction inst) throws CException;

	T visit(B2sInstruction inst) throws CException;

	T visit(B2ubInstruction inst) throws CException;

	T visit(B2uiInstruction inst) throws CException;

	T visit(B2ulInstruction inst) throws CException;

	T visit(B2usInstruction inst) throws CException;

	T visit(DivideInstruction inst) throws CException;

	T visit(I2bInstruction inst) throws CException;

	T visit(I2iInstruction inst) throws CException;

	T visit(I2lInstruction inst) throws CException;

	T visit(I2sInstruction inst) throws CException;

	T visit(I2ubInstruction inst) throws CException;

	T visit(I2uiInstruction inst) throws CException;

	T visit(I2ulInstruction inst) throws CException;

	T visit(I2usInstruction inst) throws CException;

	T visit(L2bInstruction inst) throws CException;

	T visit(L2iInstruction inst) throws CException;

	T visit(L2lInstruction inst) throws CException;

	T visit(L2sInstruction inst) throws CException;

	T visit(L2ubInstruction inst) throws CException;

	T visit(L2uiInstruction inst) throws CException;

	T visit(L2ulInstruction inst) throws CException;

	T visit(L2usInstruction inst) throws CException;

	T visit(MultiplyInstruction inst) throws CException;

	T visit(S2bInstruction inst) throws CException;

	T visit(S2iInstruction inst) throws CException;

	T visit(S2lInstruction inst) throws CException;

	T visit(S2sInstruction inst) throws CException;

	T visit(S2ubInstruction inst) throws CException;

	T visit(S2uiInstruction inst) throws CException;

	T visit(S2ulInstruction inst) throws CException;

	T visit(S2usInstruction inst) throws CException;

	T visit(SubtractInstruction inst) throws CException;

	T visit(Ub2bInstruction inst) throws CException;

	T visit(Ub2iInstruction inst) throws CException;

	T visit(Ub2lInstruction inst) throws CException;

	T visit(Ub2sInstruction inst) throws CException;

	T visit(Ub2ubInstruction inst) throws CException;

	T visit(Ub2uiInstruction inst) throws CException;

	T visit(Ub2ulInstruction inst) throws CException;

	T visit(Ub2usInstruction inst) throws CException;

	T visit(Ui2bInstruction inst) throws CException;

	T visit(Ui2iInstruction inst) throws CException;

	T visit(Ui2lInstruction inst) throws CException;

	T visit(Ui2sInstruction inst) throws CException;

	T visit(Ui2ubInstruction inst) throws CException;

	T visit(Ui2uiInstruction inst) throws CException;

	T visit(Ui2ulInstruction inst) throws CException;

	T visit(Ui2usInstruction inst) throws CException;

	T visit(Ul2bInstruction inst) throws CException;

	T visit(Ul2iInstruction inst) throws CException;

	T visit(Ul2lInstruction inst) throws CException;

	T visit(Ul2sInstruction inst) throws CException;

	T visit(Ul2ubInstruction inst) throws CException;

	T visit(Ul2uiInstruction inst) throws CException;

	T visit(Ul2ulInstruction inst) throws CException;

	T visit(Ul2usInstruction inst) throws CException;

	T visit(Us2bInstruction inst) throws CException;

	T visit(Us2iInstruction inst) throws CException;

	T visit(Us2lInstruction inst) throws CException;

	T visit(Us2sInstruction inst) throws CException;

	T visit(Us2ubInstruction inst) throws CException;

	T visit(Us2uiInstruction inst) throws CException;

	T visit(Us2ulInstruction inst) throws CException;

	T visit(Us2usInstruction inst) throws CException;

	T visit(OutputInstruction inst) throws CException;

	T visit(LoadIInstruction inst) throws CException;
}
