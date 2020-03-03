/**
 *
 */
package vaccsio;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

/**
 * @author carr
 *
 */
public abstract class VaccsIORecord {

	public static final short VACCS_ARCH = 0; /* Information regarding target architecture */
	public static final short VACCS_ASM = 1; /* An assembly (iloc) instruction */
	public static final short VACCS_EQUATION = 3; /* A line of C code */
	public static final short VACCS_FUNCTION_INV = 7; /* A function invocation event */
	public static final short VACCS_OUTPUT = 8; /* A line of C ouptut */
	public static final short VACCS_RETURN = 10; /* A function return event */
	public static final short VACCS_RETURN_ADDR = 11; /* The return address of a function invocation */
	public static final short VACCS_VAR_ACCESS = 18; /* A variable access event */
	public static final short VACCS_AST = 19; /* An AST node */
	public static final short VACCS_VARDECL = 20; /* An AST node */
	public static final short VACCS_ORDER = 21; /* An AST node */
	public static final short VACCS_ERROR = 22; /* An AST node */

	public static final String typeSeparator = "~!~";
	public static final String fieldSeparator = "|";
	public static final String NULL_ADDRESS_STRING = "--------";

	public abstract String getStringFormat();

	public abstract void write(FileOutputStream out) throws IOException;

	public void write(PrintWriter out) throws IOException {
		out.println(getStringFormat());
	}

	public void write(StringWriter outStr) {
		outStr.append(getStringFormat() + "\n");
	}
}
