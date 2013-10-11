package main.program;

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
	public abstract Tuple<Boolean, CubeXType> typecheck(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException;

	public boolean isFunction()
	{
		return false;
	}
	
	public boolean isStatement()
	{
		return false;
	}
}
