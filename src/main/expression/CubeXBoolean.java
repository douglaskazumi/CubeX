package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.type.CubeXType;

public class CubeXBoolean extends CubeXExpression 
{
	private boolean value;
	
	public CubeXBoolean(boolean value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) {
		return CubeXType.getBoolean();
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		sb.append("createBoolean(").append(value).append(", 0)");
		return sb.toString();
	}

	@Override
	public String preC() {
		// TODO Auto-generated method stub
		return "";
	}
	
}