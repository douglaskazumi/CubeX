package main.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

import main.program.CubeXProgram;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class CubeXCompiler {
	
public static void main(String[] args) throws IOException
{
	CubeXCompiler mtr = new CubeXCompiler();
	mtr.run(args);
}

public void run(String[] args) throws FileNotFoundException, IOException
{
	
//	if(args.length!=1)
//	{
//		System.out.println("usage: java lexer <filename>");
//		System.exit(-1);
//	}
	
	ANTLRInputStream input = new ANTLRInputStream("# type-checker test program 10 (stage 2)\r\n" + 
			"\r\n" + 
			"fun safeModulo(l : Integer, r : Integer, d : Integer) : Integer\r\n" + 
			"{\r\n" + 
			"	m := l%r;\r\n" + 
			"	for(i in m)\r\n" + 
			"	{\r\n" + 
			"		return i;\r\n" + 
			"	}\r\n" + 
			"	return d;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"fun safeDiv(l : Integer, r: Integer, d: Integer) : Integer\r\n" + 
			"{\r\n" + 
			"	m := l/r;\r\n" + 
			"	for(i in m)\r\n" + 
			"	{\r\n" + 
			"		return i;\r\n" + 
			"	}\r\n" + 
			"	return d;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"fun intToStr(i : Integer) : String\r\n" + 
			"{\r\n" + 
			"	pref := \"\";\r\n" + 
			"	if (i<0)\r\n" + 
			"	{\r\n" + 
			"		pref := \"-\";\r\n" + 
			"		i := i * -1;\r\n" + 
			"	}\r\n" + 
			"	ret := [character(48+safeModulo(i,10,0))];\r\n" + 
			"	while(i>0)\r\n" + 
			"	{\r\n" + 
			"		i := safeDiv(i,10,0); # we know this always works\r\n" + 
			"		ret := [character(48+safeModulo(i,10,0))] ++ ret;\r\n" + 
			"	}\r\n" + 
			"	return string(pref ++ ret);\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"fun sum (list : Iterable<Integer>) : Integer\r\n" + 
			"{\r\n" + 
			"	ret := 0;\r\n" + 
			"	for(i in list)\r\n" + 
			"	{\r\n" + 
			"		ret := ret + i;\r\n" + 
			"	}\r\n" + 
			"	return ret;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"return [intToStr(sum(1..4))];");
	//	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
	CubeXLexer lexer = new CubeXLexer(input);
	lexer.removeErrorListeners();
	lexer.addErrorListener(new LexerError());
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	CubeXParser parser = new CubeXParser(tokens);
	//parser.removeErrorListeners();
	parser.addErrorListener(new ParserError());
	
	CubeXProgram prog = parser.testprogram().x;
	if(prog.typeCheck()){
		System.out.println("accept");
	}
	else{
		System.out.println("reject");
	}
	System.out.println(prog.toString());
}
		
}

class LexerError  implements ANTLRErrorListener
{

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			BitSet arg4, ATNConfigSet arg5) {
		print();
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1,
			int arg2, int arg3, ATNConfigSet arg4) {
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
	
	private void print()
	{
		System.out.println("lexer error");
		System.exit(-1);
	}
	
}

class ParserError  implements ANTLRErrorListener
{

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			BitSet arg4, ATNConfigSet arg5) {
		System.out.println("parser error");
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
		System.out.println("parser error");
		print();
		
	}
	
	private void print()
	{
		
		System.exit(-1);
	}
	
}
