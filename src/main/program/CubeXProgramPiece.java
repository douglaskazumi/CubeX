package main.program;

import java.util.HashSet;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.util.Tuple;

public abstract class CubeXProgramPiece 
{
	protected HashSet<String>  locals = new HashSet<String>();

	public abstract Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException;
	
	public boolean isFunction()
	{
		return false;
	}
	
	public boolean isStatement()
	{
		return false;
	}
	
	public boolean isClass()
	{
		return false;
	}

	public boolean isInterface()
	{
		return false;
	}
	
	

	public void addLocal(String name)
	{
		locals.add(name);
	}

	public abstract String preC();

	public abstract String toC();
}
