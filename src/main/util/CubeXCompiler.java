package main.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import main.exceptions.TypeCheckException;
import main.program.CubeXProgram;

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
		input = new ANTLRInputStream("x := 2; if(x == 2) { x := 1 + 1; } else { x := 1 + 1; } return [\"a\"];");
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
	if(prog.typeCheck()){
		System.out.println("accept");
	}
	else{
		System.out.println("reject");
	}
	if(debug)
		System.out.println(prog.toString());
	
	try
	{
		PrintWriter writer = new PrintWriter("out.c");
		writer.println(prog.toC());
		writer.close();
		
	} catch (TypeCheckException e)
	{
		e.printStackTrace();
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
