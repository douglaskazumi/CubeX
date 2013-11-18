package main.c;

import java.util.ArrayList;
import java.util.HashSet;

import main.exceptions.ContextException;
import main.program.CubeXClass;
import main.program.CubeXProgramPiece;


public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static Constructor cons = new Constructor();
	private static CreateObject createObj = new CreateObject();
	public static HashSet<String>  locals = new HashSet<String>();
	
	public static  void addLocal(String name)
	{
		locals.add(name);
	}
	
	public static void declarationAppend(String newLine){
		declarations.append(newLine);
		declarations.append(System.getProperty("line.separator"));
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
	
	public static ArrayList<CubeXProgramPiece> allNode = new ArrayList<>();
	
}
