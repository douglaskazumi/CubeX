import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;


public class MyTestRig  implements ANTLRErrorListener {
	
public static void main(String[] args) throws IOException
{
	MyTestRig mtr = new MyTestRig();
	mtr.run(args);
}

public void run(String[] args) throws FileNotFoundException, IOException
{
	
	if(args.length!=1)
	{
		System.out.println("usage: java lexer <filename>");
		System.exit(-1);
	}

	
	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
	CubeXLexer lexer = new CubeXLexer(input);
	lexer.removeErrorListeners();
	lexer.addErrorListener(this);
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	
	tokens.fill();
	String prefix="";
	StringBuilder sb = new StringBuilder();
	for (Token t : tokens.getTokens())
	{
		if(t.getType()==-1)
			break;
		
		String result="";
		switch(lexer.getRuleNames()[t.getType()-1])
		{
		case "NAMEU":
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
		prefix=" ";
	}
	System.out.print(sb);

}

		@Override
		public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
				BitSet arg4, ATNConfigSet arg5) {
			print();
		}

		@Override
		public void reportAttemptingFullContext(Parser arg0, DFA arg1,
				int arg2, int arg3, ATNConfigSet arg4) {
			// TODO Auto-generated method stub
			print();
		}

		@Override
		public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
				int arg3, ATNConfigSet arg4) {
			// TODO Auto-generated method stub
			print();
		}

		@Override
		public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
				int arg3, String arg4, RecognitionException arg5) {
			print();
			
		}
		
		private void print()
		{
			System.out.println("error");
			System.exit(-1);
		}
		
}
