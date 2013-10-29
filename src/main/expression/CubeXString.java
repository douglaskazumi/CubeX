package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.program.CubeXClassBase;
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
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) {
		return CubeXType.getString();
	}

	@Override
	public String preC() {
		return "";
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		sb.append("createIterable_string(\"").append(value.replace("\\","\\\\")).append("\", ").append(value.length()).append(", 0, true)");
		return sb.toString();
	}
}
