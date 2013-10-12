package main.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.program.CubeXProgram;
import main.util.CubeXLexer;
import main.util.CubeXParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestType {

	public static boolean thereIsLexerError = false;
	public static boolean thereIsParserError = false;
	private static int[] indexes;

	@BeforeClass
	public static void loadInputs() {
		File dir = new File("src/main/test/tc");
		int count = dir.listFiles().length / 2;
		indexes = new int[count];
		for (int i = 0; i < count; i++) {
			indexes[i] = i+1;
		}
	}
	
	@DataPoints
	public static int[] out(){
		return indexes;
	}
	
	@Theory
	public void typeCheckTests(int index) throws IOException {
		String in_filename = "src/main/test/tc/tc_test" + index + ".in";
		String out_filename = "src/main/test/tc/tc_test" + index + ".out";
		String in_content = new String(Files.readAllBytes(Paths
				.get(in_filename)));
		String out_content = new String(Files.readAllBytes(Paths
				.get(out_filename)));
		String outputString = "";

		ANTLRInputStream input = new ANTLRInputStream(in_content);
		CubeXLexer lexer = new CubeXLexer(input);
		lexer.removeErrorListeners();
		lexer.addErrorListener(new LexerError());

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CubeXParser parser = new CubeXParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParserError());

		CubeXProgram prog = parser.testprogram().x;
		if (thereIsLexerError || thereIsParserError) {
			outputString = "reject";
		} else if (prog.typeCheck()) {
			outputString = "accept";
		} else {
			outputString = "reject";
		}

		if (!out_content.equals(outputString)) {
			System.out.println(in_content);
		}

		assertEquals(out_content, outputString);

		thereIsLexerError = false;
		thereIsParserError = false;
	}
}
