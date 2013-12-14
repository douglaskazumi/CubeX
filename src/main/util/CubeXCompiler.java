package main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import main.Optimizations.LiveVariableAnalysis;
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
	public static boolean optimizations=false;
	
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
		File f = new File("input.txt");
		if(f.exists())
			input = new ANTLRInputStream(new FileInputStream("input.txt"));
		else
		{
			System.out.println("debugging: No input file found\n");
			System.exit(-1);
		}
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
	if(CubeXCompiler.optimizations)
		prog.flattenPieces();
	try {
		if(CubeXCompiler.optimizations)
			prog.eliminateCommonSubexpressions();
		
		//Only to check the prog toString
		if(debug){
			PrintWriter output = new PrintWriter("output.txt");
			output.println(prog.toString().replace(";", ";\r\n").replace("{", "{\r\n").replace("}", "}\r\n").replace("\r\n ", "\r\n"));
			output.close();
		}
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	
	
	
	if(prog.typeCheck())
	{
		if(CubeXCompiler.optimizations)
		{
			prog.addBoxes();
			prog.simplifyFunctionBoxes();
			prog.primitivifyVariables();
			prog.reduceBoxes();
			LiveVariableAnalysis lva = new LiveVariableAnalysis(prog);
			lva.analyze();
		}
		
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
	if(debug){
		System.out.println(prog.toString());
	}
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
