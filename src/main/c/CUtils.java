package main.c;

import java.util.HashMap;

import main.expression.CubeXVariable;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;

public class CUtils {
	
	public static int tempVarIndex = 0;
	
	public static void resetStatics()
	{
		tempVarIndex = 0;
	}
	
	public static String getTempName()
	{
		return "_"+ tempVarIndex++;
	}
	
	public static String canonName(CubeXClassBase cb)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("_").append(cb==null?"":cb.getName());
		return sb.toString();
	}
	public static String canonStruct(CubeXClassBase cb)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(cb==null?"":cb.getName()).append("_t");
		return sb.toString();
	}
	
	
	
	public static String canonName(CubeXFunction fun, boolean constructor)
	{
		CubeXClassBase cb = fun.getParent();
		StringBuilder sb = new StringBuilder();
		if(constructor)
			sb.append("c");
		sb.append(canonName(cb)).append("_").append(fun.getName());
		return sb.toString();
	}
	
	public static String canonName(CubeXVariable var)
	{
		return canonName(var.getName());
	}
	
	public static String canonName(String var)
	{
		if(var.startsWith("_"))
			return "vtemp"+var;
		return "v_"+var;
	}
	
	
	
}
