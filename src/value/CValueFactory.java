/**
 * 
 */
package value;

import util.CException;

/**
 * @author carr
 *
 */
public class CValueFactory {

	/**
	 * 
	 */
	public CValueFactory() {
	}
	
	public CValue makeCValue(String type) throws CException {
		switch (type) {
		case "ByteValue":
			return new ByteValue();
		case "IntValue":
			return new IntValue();
		case "LongValue":
			return new LongValue();
		case "ShortValue":
			return new ShortValue();
		case "UnsignedByteValue":
			return new UnsignedByteValue();
		case "UnsignedIntValue":
			return new UnsignedIntValue();
		case "UnsignedLongValue":
			return new UnsignedLongValue();
		case "UnsignedShortValue":
			return new UnsignedShortValue();
		default:
			throw new CException("makeCValue: Invalid CValue type "+type);	
		}
	}

}
