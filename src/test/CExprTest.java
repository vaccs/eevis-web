package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.antlr.v4.runtime.CharStreams;

import ast.ASTNode;
import driver.CExpr;
import iloc.IlocInstruction;
import util.CException;
import util.Memory;

import java.io.IOException;
import java.io.StringReader;
import java.io.FileReader;
import java.io.BufferedReader;

public class CExprTest {

	private boolean compareReaders(BufferedReader reader1, BufferedReader reader2) throws IOException {
		String line1 = null, line2 = null;
		boolean sameContents = true;
		while (sameContents && ((line1 = reader1.readLine()) != null)) {
			line2 = reader2.readLine();
			if (line2 == null || !line1.equals(line2))
				sameContents = false;
		}
		if (sameContents)
			return reader2.readLine() == null;
		else {
			System.out.println("Line 1 = " + line1);
			System.out.println("Line 2 = " + line2);
			return false;
		}
	}

	private boolean compareFiles(String file1, String file2) throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		boolean val = compareReaders(reader1, reader2);
		reader1.close();
		reader2.close();
		return val;
	}

	private boolean compareStringAndFile(String analysis, String file) throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader(file));
		BufferedReader reader2 = new BufferedReader(new StringReader(analysis));
		boolean val = compareReaders(reader1, reader2);
		reader1.close();
		return val;
	}

	@Test
	public void testProcessCodeChar() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/char.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/char.c", false);
		}

		try {
			assertEquals(mem.get("uc").getUnsignedByteValue(), 226);
		} catch (CException e) {
			assertTrue("Variable uc not defined", false);
		}

		try {
			assertEquals(mem.get("c").getByteValue(), -15);
		} catch (CException e) {
			assertTrue("Variable c not defined", false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/char.c.vaccs.ascii", "input/base/char.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: char.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeItest1() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/itest1.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/itest1.c", false);
		}

		try {
			assertEquals(mem.get("s").getShortValue(), 1);
		} catch (CException e) {
			assertTrue("Variable s not defined", false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/itest1.c.vaccs.ascii", "input/base/itest1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest1.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeTest1() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test1.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/test1.c", false);
		}

		try {
			assertEquals(mem.get("E").getByteValue(), 10);
		} catch (CException e) {
			assertTrue("Variable E not defined", false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test1.c.vaccs.ascii", "input/base/test1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test1.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeItest2() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/itest2.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/itest2.c", false);
		}

		try {
			assertEquals(mem.get("q").getIntValue(), 2147483647);
		} catch (CException e) {
			assertTrue("Variable i not defined", false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/itest2.c.vaccs.ascii", "input/base/itest2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeItest3() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/itest3.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/itest3.c", false);
		}

		try {
			assertEquals(mem.get("q").getLongValue(), 9223372036854775807L);
		} catch (CException e) {
			assertTrue("Variable q not defined", false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/itest3.c.vaccs.ascii", "input/base/itest3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeOverflow1a() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/overflow1a.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/overflow1a.c", false);
		}

		try {
			assertEquals(mem.get("i").getIntValue(), -2147483648);
		} catch (CException e) {
			assertTrue("Variable q not defined", false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/overflow1a.c.vaccs.ascii", "input/base/overflow1a.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow1a.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeOverflow1b() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/overflow1b.c"));
		} catch (IOException e) {
			assertTrue("File not found: input/overflow1b.c", false);
		}

		try {
			assertEquals(mem.get("j").getUnsignedIntValue(), 0L);
		} catch (CException e) {
			assertTrue("Variable j not defined", false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/overflow1b.c.vaccs.ascii", "input/base/overflow1b.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow1b.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeOverflow2a() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/overflow2a.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/overflow2a.c"), false);
		}

		try {
			assertEquals(mem.get("i").getIntValue(), 2147483647);
		} catch (CException e) {
			assertTrue(("Variable q not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/overflow2a.c.vaccs.ascii", "input/base/overflow2a.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow2a.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeOverflow2b() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/overflow2b.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/overflow2b.c"), false);
		}

		try {
			assertEquals(mem.get("j").getUnsignedIntValue(), 4294967295L);
		} catch (CException e) {
			assertTrue(("Variable j not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/overflow2b.c.vaccs.ascii", "input/base/overflow2b.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow2b.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeSign() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/sign.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/sign.c"), false);
		}

		try {
			assertEquals(mem.get("u").getUnsignedShortValue(), 65533);
		} catch (CException e) {
			assertTrue(("Variable u not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/sign.c.vaccs.ascii", "input/base/sign.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: sign.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeTrunc1() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/truncation1.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/truncation.c"), false);
		}

		try {
			assertEquals(mem.get("i").getShortValue(), -32768);
		} catch (CException e) {
			assertTrue(("Variable i not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/truncation1.c.vaccs.ascii", "input/base/truncation1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: truncation1.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTrunc2() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/truncation2.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/truncation.c"), false);
		}

		try {
			assertEquals(mem.get("j").getShortValue(), -1);
		} catch (CException e) {
			assertTrue(("Variable j not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/truncation2.c.vaccs.ascii", "input/base/truncation2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: truncation2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx1() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/whatisx1.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertEquals(mem.get("x1").getUnsignedShortValue(), 65535);
		} catch (CException e) {
			assertTrue(("Variable x1 not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/whatisx1.c.vaccs.ascii", "input/base/whatisx1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx1.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeWhatisx2() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/whatisx2.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertEquals(mem.get("x2").getShortValue(), 0);
		} catch (CException e) {
			assertTrue(("Variable x2 not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/whatisx2.c.vaccs.ascii", "input/base/whatisx2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx3() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/whatisx3.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertEquals(mem.get("x3").getShortValue(), -32766);
		} catch (CException e) {
			assertTrue(("Variable x3 not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/whatisx3.c.vaccs.ascii", "input/base/whatisx3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx4() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/whatisx4.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertEquals(mem.get("x4").getUnsignedIntValue(), 4294967295L);
		} catch (CException e) {
			assertTrue(("Variable x4 not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/whatisx4.c.vaccs.ascii", "input/base/whatisx4.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx4.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx5() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/whatisx5.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertEquals(mem.get("x5").getShortValue(), -1);
		} catch (CException e) {
			assertTrue(("Variable x5 not defined"), false);
		}

		try {
			assertTrue("Files Differ",
					compareFiles("input/whatisx5.c.vaccs.ascii", "input/base/whatisx5.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx5.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest2() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test2.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test2.c"), false);
		}

		try {
			assertEquals(mem.get("z").getUnsignedShortValue(), 65531);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test2.c.vaccs.ascii", "input/base/test2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest3() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test3.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test3.c"), false);
		}

		try {
			assertEquals(mem.get("z").getUnsignedIntValue(), 2147483543);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test3.c.vaccs.ascii", "input/base/test3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest4() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test4.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test4.c"), false);
		}

		try {
			assertEquals(mem.get("z").getIntValue(), 2147483647);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test4.c.vaccs.ascii", "input/base/test4.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test4.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest5() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test5.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test5.c"), false);
		}

		try {
			assertEquals(mem.get("z").getLongValue(), 9223372034707292159L);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test5.c.vaccs.ascii", "input/base/test5.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test5.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest6() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test6.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test6.c"), false);
		}

		try {
			assertEquals(mem.get("z").getUnsignedLongValue(), 9223372034707292159L);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test6.c.vaccs.ascii", "input/base/test6.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test6.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest7() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test7.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test7.c"), false);
		}

		try {
			assertEquals(mem.get("z").getLongValue(), -9223372034707292162L);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test7.c.vaccs.ascii", "input/base/test7.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test7.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest8() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test8.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test8.c"), false);
		}

		try {
			assertEquals(mem.get("z").getUnsignedIntValue(), 2147483650L);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test8.c.vaccs.ascii", "input/base/test8.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test8.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest9() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test9.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test9.c"), false);
		}

		try {
			assertEquals(mem.get("z").getIntValue(), -2147483646);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test9.c.vaccs.ascii", "input/base/test9.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test9.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest10() {
		Memory mem = null;
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		try {
			mem = CExpr.processCodeFromFile(CharStreams.fromFileName("input/test10.c"));
		} catch (IOException e) {
			assertTrue(("File not found: input/test9.c"), false);
		}

		try {
			assertEquals(mem.get("z").getIntValue(), -51);
		} catch (CException e) {
			assertTrue(("Variable z not defined"), false);
		}

		try {
			assertTrue("Files Differ", compareFiles("input/test10.c.vaccs.ascii", "input/base/test10.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test10.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeCharString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "char c = 0xf1;\nunsigned char uc = 0xf1;\nuc = uc + c;\n";
		try {
			analysis = CExpr.processCodeFromString("char.c", input);
		} catch (IOException e1) {
			assertTrue("Exception", false);
		}

		try {
			System.out.print(analysis);
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/char.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing string for input: " + input, false);
		}

	}

	@Test
	public void testProcessCodeItest1String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "int i = -2147483648;\nshort s;\ns=(i + 1);\n";
		try {
			analysis = CExpr.processCodeFromString("itest1.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/itest1.c", false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/itest1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest1.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeTest1String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "long A = 10;\nchar B = 5;\nint C = 15;\nunsigned int D = 25;\nlong E;\nE = A + (B * (C/D));\n";
		try {
			analysis = CExpr.processCodeFromString("test1.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/test1.c", false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test1.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeItest2String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "signed int s=-2;\nunsigned int u=2;\nsigned int q;\nq=s/u;\n";
		try {
			analysis = CExpr.processCodeFromString("itest2.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/itest2.c", false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/itest2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeItest3String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "signed int s=-2;\nunsigned long u=2;\nsigned long q;\nq=s/u;\n";
		try {
			analysis = CExpr.processCodeFromString("itest3.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/itest3.c", false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/itest3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: itest3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeOverflow1aString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "int i = 2147483647;\ni = i + 1;\n";
		try {
			analysis = CExpr.processCodeFromString("overflow1a.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/overflow1a.c", false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/overflow1a.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow1a.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeOverflow1bString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned int j = 4294967295;\nj = j+1;\n";
		try {
			analysis = CExpr.processCodeFromString("overflow1b.c", input);
		} catch (IOException e) {
			assertTrue("File not found: input/overflow1b.c", false);
		}
		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/overflow1b.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow1b.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeOverflow2aString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "int i = -2147483648;\ni= i - 1;\n";
		try {
			analysis = CExpr.processCodeFromString("overflow2a.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/overflow2a.c"), false);
		}
		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/overflow2a.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow2a.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeOverflow2bString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned int j = 0;\nj = j- 1;\n";
		try {
			analysis = CExpr.processCodeFromString("overflow2b.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/overflow2b.c"), false);
		}
		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/overflow2b.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: overflow2b.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeSignString() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "int i = -3;\nunsigned short u;\nu=i;\n";
		try {
			analysis = CExpr.processCodeFromString("sign.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/sign.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/sign.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: sign.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeTrunc1String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned short u=32768;\nshort i;\ni=u;\n";
		try {
			analysis = CExpr.processCodeFromString("truncation1.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/truncation.c"), false);
		}
		try {
			assertTrue("String and File Differ",
					compareStringAndFile(analysis, "input/base/truncation1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: truncation1.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTrunc2String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned short u=65535;\nshort j;\nj=u;\n";
		try {
			analysis = CExpr.processCodeFromString("truncation2.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/truncation.c"), false);
		}

		try {
			assertTrue("String and File Differ",
					compareStringAndFile(analysis, "input/base/truncation2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: truncation2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx1String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned short x1;\nunsigned int ui1=4294967295;\nx1=ui1;\n";
		try {
			analysis = CExpr.processCodeFromString("whatisx1.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/whatisx1.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx1.c.vaccs.ascii", false);
		}

	}

	@Test
	public void testProcessCodeWhatisx2String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "short x2;\nint i2=-2147483648;\nx2=i2;\n";
		try {
			analysis = CExpr.processCodeFromString("whatisx2.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/whatisx2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx3String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "short x3=32767;\nx3=x3+3;\n";
		try {
			analysis = CExpr.processCodeFromString("whatisx3.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/whatisx3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx4String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "int y4=-1;\nunsigned int x4;\nx4=y4;\n";
		try {
			analysis = CExpr.processCodeFromString("whatisx4.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/whatisx4.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx4.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeWhatisx5String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "short x5;\nunsigned int ui5=4294967295;\nx5=ui5;\n";
		try {
			analysis = CExpr.processCodeFromString("whatisx5.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/whatisx.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/whatisx5.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: whatisx5.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest2String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "char y = -105;\nunsigned short x = 100,z;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test2.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test2.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test2.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test2.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest3String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "long y = -105;\nunsigned int x = -2147483648,z;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test3.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test3.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test3.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test3.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest4String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "long y = 9223372036854775807;\nsigned int x = -2147483648,z;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test4.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test4.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test4.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test4.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest5String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "long y = 9223372036854775807,z;\nsigned int x = -2147483648;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test5.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test5.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test5.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test5.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest6String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned long y = 9223372036854775807,z;\nsigned int x = -2147483648;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test6.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test6.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test6.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test6.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest7String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "long y = 9223372036854775807,z;\nunsigned int x = 2147483647;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test7.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test7.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test7.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test7.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest8String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned int y = 2147483647,z;\nint x = 3;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test8.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test8.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test8.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test8.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest9String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "unsigned int y = 2147483647;\nint x = 3,z;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test9.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test9.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test9.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test9.c.vaccs.ascii", false);
		}
	}

	@Test
	public void testProcessCodeTest10String() {
		ASTNode.resetNextId();
		IlocInstruction.resetNextId();
		String analysis = "";
		String input = "char x = 100,y = 105;\nchar z;\nz = x + y;\n";
		try {
			analysis = CExpr.processCodeFromString("test10.c", input);
		} catch (IOException e) {
			assertTrue(("File not found: input/test9.c"), false);
		}

		try {
			assertTrue("String and File Differ", compareStringAndFile(analysis, "input/base/test10.c.vaccs.ascii"));
		} catch (IOException e) {
			assertTrue("I/O error comparing files: test10.c.vaccs.ascii", false);
		}
	}

}
