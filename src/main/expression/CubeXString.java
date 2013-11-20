package main.expression;

import java.util.HashSet;

import main.Optimizations.Boxer;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;

public class CubeXString extends CubeXExpression {

	private String value;
	
	public CubeXString(String value)
	{
		this.value=value.replace("\"", "");
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) {
		return CubeXType.getString();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append("createIterable_string(\"").append(value.replace("\\","\\\\")).append("\", ").append(value.length()).append(", 0, true)");
		return sb.toString();
	}

	public String toString()
	{
		return "\"" + value + "\"";
	}
	
	@Override
	public HashSet<String> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		return new HashSet<String>();
	}

	@Override
	public CubeXExpression reduceBoxes()
	{
		return this;
	}
	
	@Override
	public CubeXExpression addBoxes() {
		return this;
	}
	@Override
	public CubeXExpression simplifyFunctionBoxes()
	{
		return this;
	}

	@Override
	public CubeXExpression primitivifyVariables() {
		return this;
	}
}
