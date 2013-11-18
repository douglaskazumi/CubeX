package main.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;

import main.Optimizations.LiveVariableAnalysis;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXProgramPiece;

import org.antlr.v4.runtime.*;
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
		input = new ANTLRInputStream("# Cubex Compiler Test 6 - Stage 8\r\n" + 
				"\r\n" + 
				"interface Printer\r\n" + 
				"{\r\n" + 
				"	fun print() : Iterable<String>;\r\n" + 
				"	fun line() : String;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class SinglePrinter(s:String)\r\n" + 
				"{\r\n" + 
				"	fun print() : Iterable<String>\r\n" + 
				"	{\r\n" + 
				"		return [s];\r\n" + 
				"	}\r\n" + 
				"	fun line() : String = s;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class ConstantPrinter() extends SinglePrinter\r\n" + 
				"{\r\n" + 
				"	super(\"Constant\");\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class Multiplier(s : String, n : Integer) extends SinglePrinter\r\n" + 
				"{\r\n" + 
				"	v:=\"\";\r\n" + 
				"	nn := n;\r\n" + 
				"	while(nn>0)\r\n" + 
				"	{\r\n" + 
				"		v:=string(v++s);\r\n" + 
				"		nn := nn-1;\r\n" + 
				"	}\r\n" + 
				"	super(v);\r\n" + 
				"	\r\n" + 
				"	fun print() : Iterable<String>\r\n" + 
				"	{\r\n" + 
				"		ret:=[];\r\n" + 
				"		nnn := n;\r\n" + 
				"		while (nnn>0)\r\n" + 
				"		{\r\n" + 
				"			ret:=ret++[s];\r\n" + 
				"			nnn:=nnn-1;\r\n" + 
				"		}\r\n" + 
				"		return ret;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"ps := [];\r\n" + 
				"c := 0;\r\n" + 
				"m := 0;\r\n" + 
				"for(i in input)\r\n" + 
				"{\r\n" + 
				"	m:=m+1;\r\n" + 
				"	if(c==0)\r\n" + 
				"	{\r\n" + 
				"		ps := ps ++ [ConstantPrinter()];\r\n" + 
				"		c := 1;\r\n" + 
				"	}\r\n" + 
				"	else if(c==1)\r\n" + 
				"	{\r\n" + 
				"		ps := ps ++ [SinglePrinter(i)];\r\n" + 
				"		c := 2;\r\n" + 
				"	}\r\n" + 
				"	else\r\n" + 
				"	{\r\n" + 
				"		ps := ps ++ [Multiplier(i,m)];\r\n" + 
				"		c := 0;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"out := [];\r\n" + 
				"for(p in ps)\r\n" + 
				"{\r\n" + 
				"	out := out ++ [p.line()];\r\n" + 
				"}\r\n" + 
				"for(p in ps)\r\n" + 
				"{\r\n" + 
				"	out := out ++ p.print();\r\n" + 
				"}\r\n" + 
				"return out;");
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
	if(prog.typeCheck())
	{
		
		LiveVariableAnalysis lva = new LiveVariableAnalysis(prog);
		lva.analyze();
		
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
