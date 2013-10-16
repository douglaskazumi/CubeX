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
		CubeXType varType= varCon.lookup(name);
		if(varType==null)
			throw new ContextException("Variable not in context");
		return varType;
	}

	public String getName() {
		return name;
	}
}
