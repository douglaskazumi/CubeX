package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.program.CubeXClassBase;
import main.type.CubeXType;

public class CubeXInteger extends CubeXExpression 
{
	private int value;
	
	public CubeXInteger(int value)
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
	protected CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) {
		return CubeXType.getInteger();
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		sb.append("createInteger(").append(value).append(", 0)");
		return sb.toString();
	}

	@Override
	public String preC() {
		return "";
	}
}