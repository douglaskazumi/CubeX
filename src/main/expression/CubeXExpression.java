package main.expression;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.type.CubeXType;

public abstract class CubeXExpression
{
	private CubeXType myType=null;
	
	public CubeXType getType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws TypeCheckError, ContextException
	{
		if(myType==null)
		{
			myType=calculateType(classCon, funCon, varCon, typeVarCon);
		}
		return myType;
	}
	
	protected abstract CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws TypeCheckError, ContextException;
	
}