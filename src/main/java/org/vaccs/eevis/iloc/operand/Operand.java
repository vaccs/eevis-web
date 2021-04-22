/**
 * 
 */
package org.vaccs.eevis.iloc.operand;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.Memory;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.value.CValueFactory;

/**
 * @author carr
 *
 */
public abstract class Operand {
	protected String name;
	protected int type;
	protected boolean isConstantValue = false;
	
	protected static OperandFactory opFactory = new OperandFactory();
	protected static CValueFactory cvFactory = new CValueFactory();
	
	public Operand addName(String name) {
		this.name = name;
		return this;
	}
	
	public Operand addIsConstant() {
		isConstantValue = true;
		return this;
	}
	
	public abstract String getTypeShortName();
	
	public String getName() {
		return name;
	}
	
	public String getTypedName() {
		return name + "." + getTypeShortName();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isConstant() {
		return isConstantValue;
	}
	
	public abstract Operand copy();
	
	public CValue getValue(Memory memory) throws CException {
		return memory.get(name);
	}
	
}
