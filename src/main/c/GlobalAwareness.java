package main.c;

import java.util.ArrayList;
import java.util.HashMap;

import main.exceptions.ContextException;
import main.program.CubeXClass;
import main.program.CubeXProgramPiece;
import main.util.CubeXCompiler;

public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static Constructor cons = new Constructor();
	private static CreateObject createObj = new CreateObject();
	public static HashMap<String,Boolean>  locals = new HashMap<String,Boolean>();
	public static int tempVarIndex = 0;
	
	public static void resetStatics()
	{
		declarations = new StringBuilder();
		cons = new Constructor();
		createObj = new CreateObject();
		locals = new HashMap<String,Boolean>();
		tempVarIndex = 0;
	}
	
	public static String getTempName()
	{
		return "var_cubex_temp_"+ tempVarIndex++;
	}
	
	public static  void addLocal(String name, Boolean isPrim)
	{
		if(CubeXCompiler.optimizations)
			isPrim=false;
		locals.put(name, isPrim);
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
