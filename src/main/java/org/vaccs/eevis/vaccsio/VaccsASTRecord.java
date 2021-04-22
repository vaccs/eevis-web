/**
 *
 */
package org.vaccs.eevis.vaccsio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author carr
 *
 */
public class VaccsASTRecord extends VaccsIORecord {

	private short id;
	private short parentId;
	private List<Short> children;
	private short sourceLine;
	private String source;
	private List<Short> ilocLines;

	/**
	 *
	 */
	public VaccsASTRecord() {
		ilocLines = new ArrayList<Short>();
		children = new ArrayList<Short>();
	}

	public VaccsASTRecord addId(short id) {
		this.id = id;
		return this;
	}

	public VaccsASTRecord addParentId(short parentId) {
		this.parentId = parentId;
		return this;
	}

	public VaccsASTRecord addChild(short childId) {
		children.add(childId);
		return this;
	}

	public VaccsASTRecord addSourceLine(short sourceLine) {
		this.sourceLine = sourceLine;
		return this;
	}

	public VaccsASTRecord addSource(String source) {
		this.source = source;
		return this;
	}

	public VaccsASTRecord addIlocLine(short line) {
		ilocLines.add(line);
		return this;
	}

	private String stringifyList(List<Short> l) {
		String ls = "[";
		for (Short i : l)
			ls += (i + ", ");

		return ls.substring(0, (ls.length() > 2 ? ls.length()-2 : 1)) + "]";
	}

	/* (non-Javadoc)
	 * @see vaccsio.VaccsIORecord#getStringFormat()
	 */
	@Override
	public String getStringFormat() {
		return "ast_node"
				+ VaccsIORecord.typeSeparator + id
				+ VaccsIORecord.fieldSeparator + parentId
				+ VaccsIORecord.fieldSeparator + children.size()
				+ VaccsIORecord.fieldSeparator + stringifyList(children)
				+ VaccsIORecord.fieldSeparator + sourceLine
				+ VaccsIORecord.fieldSeparator + source
				+ VaccsIORecord.fieldSeparator + ilocLines.size()
				+ VaccsIORecord.fieldSeparator + stringifyList(ilocLines);
	}

	/* (non-Javadoc)
	 * @see vaccsio.VaccsIORecord#write(java.io.FileOutputStream)
	 */
	@Override
	public void write(FileOutputStream out) throws IOException {
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(VACCS_AST).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(id).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(parentId).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort((short)children.size()).array());
		for (Short s : children)
			out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(s).array());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(sourceLine).array());
		out.write(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.nativeOrder()).putLong(source.length()).array());
		out.write(source.getBytes());
		out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort((short)ilocLines.size()).array());
		for (Short s : ilocLines)
			out.write(ByteBuffer.allocate(Short.BYTES).order(ByteOrder.nativeOrder()).putShort(s).array());

	}

}
