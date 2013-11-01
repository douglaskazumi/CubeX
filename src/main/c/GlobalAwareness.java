package main.c;

import main.exceptions.ContextException;
import main.program.CubeXClass;


public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static StringBuilder code = new StringBuilder();
	private static Constructor cons = new Constructor();
	private static CreateObject createObj = new CreateObject();
	private static int tabs = 0;
	
	public static void declarationAppend(String newLine){
		declarations.append(newLine);
		declarations.append(System.getProperty("line.separator"));
	}
	
	public static void codeAppend(String newLine) {
		if(newLine.contains("}")){
			tabs -= 1;
		}
		
		for(int i = 0; i < tabs; i++){
			code.append("\t");
		}
		
		code.append(newLine);
		code.append(System.getProperty("line.separator"));
		
		if(newLine.contains("{")){
			tabs += 1;
		}
		
		//Adds new line after closing brackets
		if(newLine.contains("}")){
			code.append(System.getProperty("line.separator"));
		}
	}
	
	public static String getDeclarations()
	{
		return declarations.toString();
	}
	
	public static String getCreateObj()
	{
		return createObj.toC();
	}
	
	public static String getConstructors()
	{
		return cons.toC();
	}
	
	public static void addClass(CubeXClass clss) throws ContextException
	{
		createObj.addClass(clss);
		cons.addClass(clss);
	}

	
}
