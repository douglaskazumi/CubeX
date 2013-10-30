package main.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

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
		input = new ANTLRInputStream("\r\n" + 
				"\r\n" + 
				"class Ff()\r\n" + 
				"{\r\n" + 
				"	fun spoon(x:String) : String = x++\"Hello\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"interface Ee\r\n" + 
				"{\r\n" + 
				"	fun spoon2(x:String) : String = x++\"Hello\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"interface Pp\r\n" + 
				"{\r\n" + 
				"	fun spoon3(x:String) : String = x++\"Hello\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class Bb() extends Ff&Ee&Pp\r\n" + 
				"{\r\n" + 
				"	super();\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class Aa() extends Ff\r\n" + 
				"{\r\n" + 
				"	super();\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"interface Cc extends Ee\r\n" + 
				"{\r\n" + 
				"	fun tastic(cow : Iterable<Ff> & Thing, lemon : String) : Iterable<String>\r\n" + 
				"	{\r\n" + 
				"		return [\"helloworld\"];\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"class Xx() extends Aa&Cc&Pp\r\n" + 
				"{\r\n" + 
				"	super();\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"j := Xx();\r\n" + 
				"v := Bb();\r\n" + 
				"k := [j,v];\r\n" + 
				"\r\n" + 
				"return j.tastic(k, k.spoon3());");
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
