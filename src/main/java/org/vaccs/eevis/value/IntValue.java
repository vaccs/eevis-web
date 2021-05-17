/**
 * 
 */
package org.vaccs.eevis.value;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.visitor.EvalVisitor;

/**
 * @author carr
 *
 */
public class IntValue extends CValue {

	private int value;
	/**
	 * 
	 */
	public IntValue() {
	}
	
	public IntValue addValue(int value) {
		this.value = value;
		return this;
	}

	/* (non-Javadoc)
	 * @see util.CValue#getCValue()
	 */
	@Override
	public Number getJavaValue() {
		return new Integer(value);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public CValue add(CValue rValue2) throws CException {		
		return new IntValue().addValue((int)(value + ((IntValue)rValue2).getValue()));
	}

	@Override
	public CValue sub(CValue rValue2) throws CException {
		return new IntValue().addValue((int)(value - ((IntValue)rValue2).getValue()));
	}

	@Override
	public CValue mul(CValue rValue2) throws CException {
		return new IntValue().addValue((int)(value * ((IntValue)rValue2).getValue()));
	}

	@Override
	public CValue div(CValue rValue2) throws CException {
		return new IntValue().addValue((int)(value / ((IntValue)rValue2).getValue()));
	}

	@Override
	public byte getByteValue() {
		return (byte)value;
	}

	@Override
	public short getShortValue() {
		return (short)value;
	}

	@Override
	public int getIntValue() {
		return value;
	}

	@Override
	public long getLongValue() {
		return value;
	}

	@Override
	public int getUnsignedByteValue() {
		return value & UnsignedByteValue.bitmask;
	}

	@Override
	public int getUnsignedShortValue() {
		return value & UnsignedShortValue.bitmask;
	}

	@Override
	public long getUnsignedIntValue() {
		return (long)value & UnsignedIntValue.bitmask;
	}

	@Override
	public long getUnsignedLongValue() {
		return value;
	}

	@Override
	public String getType() {
		return CTypeTables.typeName[CTypeTables.CINT];

	}

}
