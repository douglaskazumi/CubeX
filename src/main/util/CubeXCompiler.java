package main.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class CubeXCompiler {
	
	public static boolean debug=true;
	
public static void main(String[] args) throws IOException
{
	CubeXCompiler mtr = new CubeXCompiler();
	mtr.run(args);
}

public void run(String[] args) throws FileNotFoundException, IOException
{
	
	if(!debug)
	{
		if(args.length!=1)
		{
			System.out.println("usage: java lexer <filename>");
			System.exit(-1);
		}
	}
	
	ANTLRInputStream input=null;
	if(debug)
	{
		input = new ANTLRInputStream("# Cubex Compiler Test 2 - Stage 2\r\n" + 
				"\r\n" + 
				"fun safeDiv( l : Integer, r : Integer, d : Integer) : Integer\r\n" + 
				"{\r\n" + 
				"	m := l/r;\r\n" + 
				"	for(i in m)\r\n" + 
				"	{\r\n" + 
				"		return i;\r\n" + 
				"	}\r\n" + 
				"	return d;\r\n" + 
				"}\r\n" + 
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
				"fun intToStr(i : Integer) : String\r\n" + 
				"{\r\n" + 
				"	pref := \"\";\r\n" + 
				"	if (i<0)\r\n" + 
				"	{\r\n" + 
				"		pref := \"-\";\r\n" + 
				"		i := i * -1;\r\n" + 
				"	}\r\n" + 
				"	ret := [character(48+safeModulo(i,10,0))];\r\n" + 
				"	i := safeDiv(i,10,0);\r\n" + 
				"	while(i>0)\r\n" + 
				"	{\r\n" + 
				"		ret := [character(48+safeModulo(i,10,0))] ++ ret;\r\n" + 
				"		i := safeDiv(i,10,0);\r\n" + 
				"	}\r\n" + 
				"	return string(pref ++ ret);\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"for(i in (1..5) ++ [6])\r\n" + 
				"   return [intToStr(i)];\r\n" + 
				"\r\n" + 
				"return [\"nope\"];");
	}
	else
	{
		input = new ANTLRInputStream(new FileInputStream(args[0]));
	}
	
	CubeXLexer lexer = new CubeXLexer(input);
	lexer.removeErrorListeners();
	lexer.addErrorListener(new LexerError());
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	CubeXParser parser = new CubeXParser(tokens);
	if(!debug)
		parser.removeErrorListeners();
	parser.addErrorListener(new ParserError());
	
	CubeXProgram prog = parser.testprogram().x;
	prog.flattenPieces();
	if(prog.typeCheck())
	{
		prog.addBoxes();
		prog.simplifyFunctionBoxes();
		prog.primitivifyVariables();
		prog.reduceBoxes();
//		LiveVariableAnalysis lva = new LiveVariableAnalysis(prog);
//		lva.analyze();
		
		try
		{
			PrintWriter writer = new PrintWriter("out.c");
			writer.println(prog.toC());
			writer.close();
			
		} catch (TypeCheckException | ContextException e)
		{
			e.printStackTrace();
		}
	}
	else{
		System.out.println("reject");
		System.exit(0);
	}
	if(debug)
		System.out.println(prog.toString());
	
//	try
//	{
//		PrintWriter writer = new PrintWriter("out.c");
//		writer.println(prog.toC());
//		writer.close();
//		
//	} catch (TypeCheckException | ContextException e)
//	{
//		e.printStackTrace();
//	}
	
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
		System.out.println("reject");
		System.exit(-1);
	}
	
}

class ParserError  implements ANTLRErrorListener
{

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			BitSet arg4, ATNConfigSet arg5) {
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
		print();
		
	}
	
	private void print()
	{
		System.out.println("reject");
		System.exit(-1);
	}
	
}
