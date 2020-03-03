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
public class ShortValue extends CValue {

	private short value;
	
	/**
	 * 
	 */
	public ShortValue() {

	}

	/* (non-Javadoc)
	 * @see util.CValue#getCValue()
	 */
	@Override
	public Number getJavaValue() {
		return new Short(value);
	}

	/**
	 * @return the value
	 */
	public short getValue() {
		return value;
	}
	
	public ShortValue addValue(short val) {
		value = val;
		return this;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(short value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see util.CValue#add(util.CValue)
	 */
	@Override
	public CValue add(CValue rValue2) throws CException {
		return new ShortValue().addValue((short)(value + ((ShortValue)rValue2).getValue()));
	}

	@Override
	public CValue sub(CValue rValue2) throws CException {
		return new ShortValue().addValue((short)(value - ((ShortValue)rValue2).getValue()));
	}

	@Override
	public CValue mul(CValue rValue2) throws CException {
		return new ShortValue().addValue((short)(value * ((ShortValue)rValue2).getValue()));
	}

	@Override
	public CValue div(CValue rValue2) throws CException {
		return new ShortValue().addValue((short)(value / ((ShortValue)rValue2).getValue()));
	}

	@Override
	public byte getByteValue() {
		return (byte)value;
	}

	@Override
	public short getShortValue() {
		return value;
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
		return (int)value & UnsignedByteValue.bitmask;
	}

	@Override
	public int getUnsignedShortValue() {
		return (int)value & UnsignedShortValue.bitmask;
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
		return CTypeTables.typeName[CTypeTables.CSHORT];
	}

}
