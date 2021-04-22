/**
 * 
 */
package org.vaccs.eevis.value;

import java.math.BigInteger;

import org.vaccs.eevis.util.CException;

/**
 * @author carr
 *
 */
public abstract class CValue {
	
	protected static CValueFactory cvFactory = new CValueFactory();
	protected byte[] val;
	
	/**
	 * @return the val
	 */
	public byte[] get() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void set(byte[] val) {
		this.val = val;
	}

    public CValue addValue(byte[] val) {
    		set(val);
    		return this;
    }
	
    public BigInteger getBigIntegerValue()  {
    		return new BigInteger(val);
    }
    
    public abstract String getType();
    
    public abstract Number getJavaValue();
 
	public abstract CValue add(CValue rValue2) throws CException;
	public abstract CValue sub(CValue rValue2) throws CException;
	public abstract CValue mul(CValue rValue2) throws CException;
	public abstract CValue div(CValue rValue2) throws CException;
	
	public abstract byte getByteValue();
	public abstract short getShortValue();
	public abstract int getIntValue();
	public abstract long getLongValue();
	
	public abstract int getUnsignedByteValue();
	public abstract int getUnsignedShortValue();
	public abstract long getUnsignedIntValue();
	public abstract long getUnsignedLongValue();
}
