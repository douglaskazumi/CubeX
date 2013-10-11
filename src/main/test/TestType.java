package main.test;

import static org.junit.Assert.*;

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
import org.junit.Test;

class LexerError implements ANTLRErrorListener {

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			BitSet arg4, ATNConfigSet arg5) {
		print();
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
			int arg3, ATNConfigSet arg4) {
		print();
	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
			int arg3, ATNConfigSet arg4) {
		print();
	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
			int arg3, String arg4, RecognitionException arg5) {
		print();

	}

	private void print() {
		TestType.thereIsLexerError = true;	}

}

class ParserError  implements ANTLRErrorListener
{

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			BitSet arg4, ATNConfigSet arg5) {
		//System.out.println("parser error");
		print();
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1,
			int arg2, int arg3, ATNConfigSet arg4) {
	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
			int arg3, ATNConfigSet arg4) {
	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
			int arg3, String arg4, RecognitionException arg5) {
		//System.out.println("parser error");
		print();
		
	}
	
	private void print()
	{
		TestType.thereIsParserError = true;
	}
	
}

public class TestType {

	public static boolean thereIsLexerError = false;
	public static boolean thereIsParserError = false;

	@Test
	public void lexerTests() throws IOException {
		for (int i = 1; i <= 6; i++) {
			String in_filename = "tests/lexer_test" + i + ".in";
			String out_filename = "tests/lexer_test" + i + ".out";
			String in_content = new String(Files.readAllBytes(Paths
					.get(in_filename)));
			String out_content = new String(Files.readAllBytes(Paths
					.get(out_filename)));

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
			assertEquals(out_content, outputString);
			thereIsLexerError = false;
		}

	}

	@Test
	public void parserTests() throws IOException {
		for (int i = 1; i <= 6; i++) {
			String in_filename = "tests/parser_test" + i + ".in";
			String out_filename = "tests/parser_test" + i + ".out";
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
			
			System.out.println(i);
			if (!thereIsParserError && !thereIsLexerError) {
				outputString = prog.toString();
			} else if  (thereIsParserError){
				outputString = "parser error";
			}
			else {
				outputString = "lexer error";
			}
			assertEquals(out_content, outputString);
			
			thereIsLexerError = false;
			thereIsParserError = false;
		}
	}

}
