package main.expression;


import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;

public abstract class CubeXExpression
{
	private CubeXType myType=null;
	
	public CubeXType getType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException
	{
		if(myType==null)
		{
			myType=calculateType(classCon, funCon, varCon, typeVarCon);
		}
		return myType;
	}
	
	protected abstract CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException;
	
	public abstract void toC();
}