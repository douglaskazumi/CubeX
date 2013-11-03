package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;

public class CubeXBoolean extends CubeXExpression 
{
	private boolean value;
	
	public CubeXBoolean(boolean value)
	{
		this.value = value;
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) {
		return CubeXType.getBoolean();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append("createBoolean(").append(value).append(", 0)");
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}
	
}