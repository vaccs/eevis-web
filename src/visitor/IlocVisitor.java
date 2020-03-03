/**
 * 
 */
package visitor;

import iloc.AddInstruction;
import iloc.DivideInstruction;
import iloc.LoadIInstruction;
import iloc.MultiplyInstruction;
import iloc.OutputInstruction;
import iloc.SubtractInstruction;
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
