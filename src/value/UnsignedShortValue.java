package value;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import util.CException;
import util.CTypeTables;
import value.CValue;
import visitor.EvalVisitor;

/**
 * 
 */

/**
 * @author carr
 *
 */
public class UnsignedShortValue extends CValue {

	private int value;
	static final int bitmask = 0xffff;
	
	/**
	 * 
	 */
	public UnsignedShortValue() {

	}

	/* (non-Javadoc)
	 * @see util.CValue#getCValue()
	 */
	@Override
	public Number getJavaValue() {
		return new Integer(value & bitmask);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value & bitmask;
	}
	
	public UnsignedShortValue addValue(int val) {
		value = val & bitmask;
		return this;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value & bitmask;
	}

	/* (non-Javadoc)
	 * @see util.CValue#add(util.CValue)
	 */
	@Override
	public CValue add(CValue rValue2) throws CException {
		return new UnsignedShortValue().addValue((short)(getValue() + ((UnsignedShortValue)rValue2).getValue()));
	}

	@Override
	public CValue sub(CValue rValue2) throws CException {
		return new UnsignedShortValue().addValue((short)(getValue() - ((UnsignedShortValue)rValue2).getValue()));
	}

	@Override
	public CValue mul(CValue rValue2) throws CException {
		return new UnsignedShortValue().addValue((short)(getValue() * ((UnsignedShortValue)rValue2).getValue()));
	}

	@Override
	public CValue div(CValue rValue2) throws CException {
		return new UnsignedShortValue().addValue((short)(getValue() / ((UnsignedIntValue)rValue2).getValue()));
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
		return CTypeTables.typeName[CTypeTables.CUSHORT];
	}

}
