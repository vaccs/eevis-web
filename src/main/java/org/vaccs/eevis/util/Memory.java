/**
 * 
 */
package org.vaccs.eevis.util;

import java.util.HashMap;
import java.util.Hashtable;

import org.vaccs.eevis.value.CValue;

/**
 * @author carr
 *
 */
public class Memory extends Hashtable<String, CValue> {

	private HashMap<String,Integer> addrMap = new HashMap<String,Integer>();
	private int nextAddress = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CValue put(String key, CValue cval) {
		if (!addrMap.containsKey(key))
			addrMap.put(key, nextAddress++);
		return super.put(key, cval);
	}
	
	public CValue get(String key) throws CException {
		if (super.containsKey(key))
			return super.get(key);
		else
			throw new CException("Reference to undefined variable: "+key);
	}
	
	public int getAddress(String key) {
		return addrMap.get(key);
	}

}
