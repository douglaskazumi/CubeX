package main.c;

import main.exceptions.ContextException;
import main.program.CubeXClass;


public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static Constructor cons = new Constructor();
	private static CreateObject createObj = new CreateObject();
	
	public static void declarationAppend(String newLine){
		declarations.append(newLine);
		declarations.append(System.getProperty("line.separator"));
	}
	
	public static void addClass(CubeXClass clss) throws ContextException
	{
		createObj.addClass(clss);
		cons.addClass(clss);
	}

	
}
