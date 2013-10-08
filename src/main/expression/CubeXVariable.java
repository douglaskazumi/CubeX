package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;


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
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon) throws ContextException, TypeCheckException
	{
		CubeXVariable var= varCon.lookup(name);
		if(var==null)
			throw new ContextException();
		return var.getType(classCon, funCon, varCon, typeVarCon);
	}
}
