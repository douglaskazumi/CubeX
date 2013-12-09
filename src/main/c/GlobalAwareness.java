package main.c;

import java.util.ArrayList;
import java.util.HashMap;

import main.exceptions.ContextException;
import main.program.CubeXClass;
import main.program.CubeXClassYielder;
import main.program.CubeXProgramPiece;

public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static Constructor cons = new Constructor();
	private static YielderStructs yieldstructs = new YielderStructs();
	private static CreateObject createObj = new CreateObject();
	public static HashMap<String,Boolean>  locals = new HashMap<String,Boolean>();
	public static int tempVarIndex = 0;
	public static int yielderId = 1;
	
	public static int getNextYieldId()
	{
		return yielderId++;
	}
	
	public static String getTempName()
	{
		return "var_cubex_temp_"+ tempVarIndex++;
	}
	
	public static  void addLocal(String name, Boolean isPrim)
	{
		locals.put(name, isPrim);
	}
	
	public static void declarationAppend(String newLine){
		declarations.append(newLine);
		declarations.append(System.getProperty("line.separator"));
	}
	
	public static void addYielder(CubeXClassYielder yielderclss)
	{
		yieldstructs.addYielder(yielderclss);
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
	
	public static String getYielders()
	{
		return yieldstructs.toC();
		
	}
	public static void addClass(CubeXClass clss) throws ContextException
	{
		createObj.addClass(clss);
		cons.addClass(clss);
	}
	
	public static ArrayList<CubeXProgramPiece> allNode = new ArrayList<>();
	
}
