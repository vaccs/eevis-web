package org.vaccs.eevis.value;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.vaccs.eevis.util.CException;
import org.vaccs.eevis.util.CTypeTables;
import org.vaccs.eevis.value.CValue;
import org.vaccs.eevis.visitor.EvalVisitor;

/**
 * 
 */

/**
 * @author carr
 *
 */
public class UnsignedIntValue extends CValue {

	private long value;
	static final long bitmask = 0xffffffffL;
	
	/**
	 * 
	 */
	public UnsignedIntValue() {

	}

	/* (non-Javadoc)
	 * @see util.CValue#getCValue()
	 */
	@Override
	public Number getJavaValue() {
		return new Long(value & bitmask);
	}

	/**
	 * @return the value
	 */
	public long getValue() {
		return value & bitmask;
	}
	
	public UnsignedIntValue addValue(long val) {
		value = val & bitmask;
		return this;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value & bitmask;
	}

	/* (non-Javadoc)
	 * @see util.CValue#add(util.CValue)
	 */
	@Override
	public CValue add(CValue rValue2) throws CException {
		return new UnsignedIntValue().addValue((int)(getValue() + ((UnsignedIntValue)rValue2).getValue()));
	}

	@Override
	public CValue sub(CValue rValue2) throws CException {
		return new UnsignedIntValue().addValue((int)(getValue() - ((UnsignedIntValue)rValue2).getValue()));
	}

	@Override
	public CValue mul(CValue rValue2) throws CException {
		return new UnsignedIntValue().addValue((int)(getValue() * ((UnsignedIntValue)rValue2).getValue()));
	}

	@Override
	public CValue div(CValue rValue2) throws CException {
		return new UnsignedIntValue().addValue((int)(getValue() / ((UnsignedIntValue)rValue2).getValue()));
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
		return (int)value;
	}

	@Override
	public long getLongValue() {
		return value;
	}

	@Override
	public int getUnsignedByteValue() {
		return (int)(value & UnsignedIntValue.bitmask);
	}

	@Override
	public int getUnsignedShortValue() {
		return (int)(value & UnsignedShortValue.bitmask);
	}

	@Override
	public long getUnsignedIntValue() {
		return value;
	}

	@Override
	public long getUnsignedLongValue() {
		return value;
	}

	@Override
	public String getType() {
		return CTypeTables.typeName[CTypeTables.CUINT];
	}

}
