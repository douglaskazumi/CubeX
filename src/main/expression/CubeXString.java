package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.type.CubeXType;

public class CubeXString extends CubeXExpression {

	private String value;
	
	public CubeXString(String value)
	{
		this.value=value;
	}
	
	public String toString()
	{
		return value;
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon) {
		return CubeXType.getString();
	}
}
