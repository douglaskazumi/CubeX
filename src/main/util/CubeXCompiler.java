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
	
	ANTLRInputStream input = new ANTLRInputStream("# typechecker test program 14 (stage 6)\r\n" + 
			"\r\n" + 
			"interface List<T>\r\n" + 
			"{\r\n" + 
			"	fun getFirst() : Iterable<T>;\r\n" + 
			"	fun getRest() : List<T>;\r\n" + 
			"	fun getIterable() : Iterable<T>;\r\n" + 
			"	fun append(l:List<T>) : List<T>;\r\n" + 
			"	fun reverse() : List<T>;\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"class Nil<T>() extends List<T>\r\n" + 
			"{\r\n" + 
			"	super();\r\n" + 
			"	fun getFirst() : Iterable<T> = [];\r\n" + 
			"	fun getRest() : List<T> = Nil<T>();\r\n" + 
			"	fun getIterable() : Iterable<T> = [];\r\n" + 
			"	fun append(l:List<T>) : List<T> = l;\r\n" + 
			"	fun reverse() : List<T> = Nil<T>();\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"class Cons<T>(elem : T,rest : List<T>) extends List<T>\r\n" + 
			"{\r\n" + 
			"	super();\r\n" + 
			"	fun getFirst() : Iterable<T> = [elem];\r\n" + 
			"	fun getRest() : List<T> = rest;\r\n" + 
			"	fun getIterable() : Iterable<T>\r\n" + 
			"	{\r\n" + 
			"		return [elem] ++ rest.getIterable();\r\n" + 
			"	}\r\n" + 
			"	fun append(l:List<T>) : List<T>\r\n" + 
			"	{\r\n" + 
			"		return Cons<T>(elem,rest.append(l));\r\n" + 
			"	}\r\n" + 
			"	fun reverse() : List<T>\r\n" + 
			"	{\r\n" + 
			"		return rest.reverse().append(Cons<T>(elem,Nil<T>()));\r\n" + 
			"	}\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"fun strToCons<T>(iter : Iterable<T>) : List<T>\r\n" + 
			"{\r\n" + 
			"	ret := Nil<T>().reverse();\r\n" + 
			"	for(c in iter)\r\n" + 
			"	{\r\n" + 
			"		ret := Cons<T>(c,ret);\r\n" + 
			"	}\r\n" + 
			"	return ret.reverse();\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"return [string(strToCons<Character>(\"Hello World!\").getIterable())];");
	//	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
	CubeXLexer lexer = new CubeXLexer(input);
	lexer.removeErrorListeners();
	lexer.addErrorListener(new LexerError());
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	CubeXParser parser = new CubeXParser(tokens);
	parser.removeErrorListeners();
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
