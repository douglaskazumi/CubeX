package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.type.CubeXType;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

public class CubeXVariable extends CubeXExpression 
{
	private String name;
	
	public CubeXVariable(String name)
	{
		this.name=name;
	}
	
	public String toString()
	{
		return name;
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon) throws TypeCheckError,	ContextException
	{
		return varCon.lookup(name).getType(classCon, funCon, varCon, typeVarCon);
	}
}
