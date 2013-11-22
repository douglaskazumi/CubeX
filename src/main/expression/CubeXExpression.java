package main.expression;


import java.util.ArrayList;
import java.util.HashSet;

import main.c.CUtils;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;

public abstract class CubeXExpression
{
	protected CubeXType myType=null;
	
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
	
	public abstract HashSet<String> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions);
	
	public abstract CubeXExpression addBoxes();
	public abstract CubeXExpression reduceBoxes();
	public abstract CubeXExpression simplifyFunctionBoxes();
	public abstract CubeXExpression primitivifyVariables();
	
	public boolean equals(CubeXExpression other){
		return false;
	}
	public boolean contains(CubeXVariable var){
		return false;
	}
	public void replace(CubeXVariable oldVar, CubeXExpression newVar){
	}
	
	public boolean isVariable() {
		return false;
	}
	
	public boolean isBoolean()
	{
		return false;
	}
	
	public boolean isFunctionCall() {
		return false;
	}
	
	public boolean isBoxer()
	{
		return false;
	}
	
	public boolean isAppend() {
		return false;
	}
	
	public boolean isIterable() {
		return false;
	}
	
	public boolean isInteger() {
		return false;
	}
	
	public boolean isString() {
		return false;
	}
}

