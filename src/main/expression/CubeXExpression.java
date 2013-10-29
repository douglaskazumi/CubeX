package main.expression;


import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.type.CubeXType;

public abstract class CubeXExpression
{
	private CubeXType myType=null;
	
	public CubeXType getType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon, boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException
	{
		return calculateType(classCon, funCon, varCon, typeVarCon, setField, par);
	}
	
	protected abstract CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException;
	
	public abstract String preC();
	
	public abstract String toC();
}
