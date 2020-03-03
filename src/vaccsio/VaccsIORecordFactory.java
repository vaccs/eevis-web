/**
 *
 */
package vaccsio;

/**
 * @author carr
 *
 */
public class VaccsIORecordFactory {

	/**
	 *
	 */
	public VaccsIORecordFactory() {
	}

	public VaccsIORecord makeVaccIORecord(String type) {
		VaccsIORecord vior = null;
		switch (type) {
		case "VaccsArchitectureRecord":
			vior = new VaccsArchitectureRecord();
			break;
		case "VaccsAssemblyRecord":
			vior = new VaccsAssemblyRecord();
			break;
		case "VaccsASTRecord":
			vior = new VaccsASTRecord();
			break;
		case "VaccsEquationRecord":
			vior = new VaccsEquationRecord();
			break;
		case "VaccsErrorRecord":
			vior = new VaccsErrorRecord();
			break;
		case "VaccsFunctionInvocationRecord":
			vior = new VaccsFunctionInvocationRecord();
			break;
		case "VaccsOutputRecord":
			vior = new VaccsOutputRecord();
			break;
		case "VaccsReturnAddressRecord":
			vior = new VaccsReturnAddressRecord();
			break;
		case "VaccsReturnRecord":
			vior = new VaccsReturnRecord();
			break;
		case "VaccsVariableAccessRecord":
			vior = new VaccsVariableAccessRecord();
			break;
		case "VaccsVarDeclRecord":
			vior = new VaccsVarDeclRecord();
			break;
		case "VaccsTraversalRecord":
			vior = new VaccsTraversalRecord();
			break;
		default:
			throw new RuntimeException("Invalid VaccsIORecord type "+vior);
		}

		return vior;
	}
}
