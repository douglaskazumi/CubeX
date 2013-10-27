package main.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;

import main.program.CubeXProgram;
import main.util.CubeXLexer;
import main.util.CubeXParser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

class LexerError implements ANTLRErrorListener {

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
		print();
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
		print();
	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
		print();
	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
		print();

	}

	private void print() {
		TestType.thereIsLexerError = true;
	}

}

class ParserError implements ANTLRErrorListener {

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
		// System.out.println("parser error");
		print();
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, ATNConfigSet arg4) {
	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
		// System.out.println("parser error");
		print();

	}

	private void print() {
		TestType.thereIsParserError = true;
	}

}

public class TestType {

	public static boolean thereIsLexerError = false;
	public static boolean thereIsParserError = false;
	public static BufferedWriter errorLog;
	
	@BeforeClass
	public static void setUp(){
		String fileName = "log" + System.currentTimeMillis();
		File file = new File("tests/logs/");
		//if dir does not exists, create
		if (!file.exists()) {
			file.mkdir();
		}
		
		file = new File("tests/logs/" + fileName + ".txt");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		errorLog = new BufferedWriter(fw);
	}
	
	@AfterClass
	public static void cleanUp(){
		try {
			errorLog.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// *

	@Test
	public void lexerTests() throws IOException {
		File dir = new File("tests/lexer");
		int count = dir.listFiles().length / 2 + 1;
		for (int i = 1; i < count; i++) {
			System.out.println("Lexer " + i + "\n");
			thereIsLexerError = false;
			thereIsParserError = false;
			String in_filename = "tests/lexer/lexer_test" + i + ".in";
			String out_filename = "tests/lexer/lexer_test" + i + ".out";
			String in_content = new String(Files.readAllBytes(Paths.get(in_filename)));
			String out_content = new String(Files.readAllBytes(Paths.get(out_filename)));

			ANTLRInputStream input = new ANTLRInputStream(in_content);
			CubeXLexer lexer = new CubeXLexer(input);
			lexer.removeErrorListeners();
			lexer.addErrorListener(new LexerError());

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			tokens.fill();
			String prefix = "";
			String outputString = "";
			StringBuilder sb = new StringBuilder();
			for (Token t : tokens.getTokens()) {
				if (t.getType() == -1)
					break;
				String result = "";
				switch (lexer.getRuleNames()[t.getType() - 1]) {
				case "NAMEU":
				case "NAMEUSINGLE":
					result = "Name";
					break;
				case "NAMEL":
					result = "name";
					break;
				case "STRING":
					result = "\"\"";
					break;
				case "INT":
					result = "0";
					break;
				case "TRUE":
				case "FALSE":
					result = "true";
					break;
				default:
					result = t.getText();
					break;
				}

				sb.append(prefix).append(result);
				prefix = " ";
			}

			if (!thereIsLexerError) {
				outputString = sb.toString();
			} else {
				outputString = "error";
			}
			if(out_content.equals(outputString)){
				errorLog.write("--Lexer test file " + i + " succeeded\n");
			}
			else{
				errorLog.write("--Lexer test file " + i + " FAILED\n");
			}
			assertEquals(out_content, outputString);
		}

	}

	/**/

	// *
	@Test
	public void parserTests() throws IOException {
		File dir = new File("tests/parser");
		int count = dir.listFiles().length / 2 + 1;
		for (int i = 1; i < count; i++) {
			System.out.println("Parser " + i + "\n");
			thereIsLexerError = false;
			thereIsParserError = false;
			String in_filename = "tests/parser/parser_test" + i + ".in";
			String out_filename = "tests/parser/parser_test" + i + ".out";
			String in_content = new String(Files.readAllBytes(Paths.get(in_filename)));
			String out_content = new String(Files.readAllBytes(Paths.get(out_filename)));
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

			if (!thereIsLexerError && !thereIsParserError) {
				outputString = prog.toString();
			} else if (thereIsLexerError) {
				outputString = "lexer error";
			} else {
				outputString = "parser error";
			}
			if(out_content.equals(outputString)){
				errorLog.write("-----Parser test file " + i + " succeeded\n");
			}
			else{
				errorLog.write("-----Parser test file " + i + " FAILED\n");
			}
			assertEquals(out_content, outputString);
		}
	}

	/**/
	@Test
	public void typeCheckTests() throws IOException {
		File dir = new File("tests/typeCheck");
		int count = dir.listFiles().length / 2 + 1;
		for (int i = 1; i < count; i++) {
			System.out.println("Type checking " + i + "\n");
			thereIsLexerError = false;
			thereIsParserError = false;
			String in_filename = "tests/typeCheck/tc_test" + i + ".in";
			String out_filename = "tests/typeCheck/tc_test" + i + ".out";
			String in_content = new String(Files.readAllBytes(Paths.get(in_filename)));
			String out_content = new String(Files.readAllBytes(Paths.get(out_filename)));
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
			
			if(out_content.equals(outputString)){
				if(outputString == "accept"){
					errorLog.write("-------Type checking test file " + i + " succeeded\n");
				}
				else{
					if(!thereIsLexerError && !thereIsParserError)
						errorLog.write("-------Type checking test file " + i + " succeeded. Msg: " + prog.getErrorMsg() + "\n");
					else
						errorLog.write("-------Type checking test file " + i + " succeeded. Msg: lexer/parser error\n");
				}
			}
			else{
				if(!thereIsLexerError && !thereIsParserError)
					errorLog.write("-------Type checking test file " + i + " FAILED. Msg: " + prog.getErrorMsg() + "\n");
				else
					errorLog.write("-------Type checking test file " + i + " FAILED. Msg: lexer/parser error\n");
			}
			assertEquals(out_content, outputString);
		}
	}

}
