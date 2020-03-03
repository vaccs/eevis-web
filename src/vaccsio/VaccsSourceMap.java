/**
 * 
 */
package vaccsio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * @author carr
 *
 */
public class VaccsSourceMap extends HashMap<String, Vector<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5714313918877047125L;

	public VaccsSourceMap() {
		super();
	}
	
	public void putSourceLine(String path, String sourceLine, int lineNum) {
		Vector<String> sourceList = (Vector<String>)super.get(path);
		if (sourceList == null) {
			sourceList = new Vector<String>();
			super.put(path, sourceList);
		}
		
		sourceList.setSize(lineNum+1);
		sourceList.setElementAt(sourceLine,lineNum);
	}
	
	public String getSourceLine(String path, int lineNum) {
		String sourceLine = null;
		Vector<String> sourceList = super.get(path);
		if (sourceList != null)
			sourceLine = sourceList.elementAt(lineNum);
		
		return sourceLine;
	}
}
