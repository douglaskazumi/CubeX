package main.expression;


import java.util.ArrayList;

import main.c.CUtils;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;

public abstract class CubeXExpression
{
	private CubeXType myType=null;
	
	protected ArrayList<String> temps = new ArrayList<>();
	
	public CubeXType getType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon, boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException
	{
		if(myType==null || force)
		{
			myType = calculateType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		}
		return myType;
	}
	
	public CubeXType getTypeUnsafe()
	{
		return myType;
	}

	protected abstract CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException;
	
	public abstract String preC(CubeXProgramPiece par);
	
	public abstract String toC(CubeXProgramPiece par);
	
	public String postC(CubeXProgramPiece par)
	{
		StringBuilder sb = new StringBuilder();
		for(String var : temps)
		{
		sb.append("\t\tgc(gc_dec(").append(CUtils.canonName(var)).append("));\n");
		sb.append("\t\t").append(CUtils.canonName(var)).append(" = NULL;\n");
		}
		return sb.toString();
	}
	
	public ArrayList<String> getTemps()
	{
		return temps;
	}
}
