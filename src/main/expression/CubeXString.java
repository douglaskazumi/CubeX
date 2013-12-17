package main.expression;

import java.util.HashSet;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
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
	protected CubeXType calculateType(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) {
		return CubeXType.getString();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		if(value.equals(""))
			sb.append("NULL");
		else
			sb.append("createIterable_string(\"").append(value.replace("\\","\\\\")).append("\", ").append(value.length()).append(", 0, true)");
		return sb.toString();
	}

	public String toString()
	{
		return "\"" + value + "\"";
	}
	
	@Override
	public HashSet<CubeXVariable> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		return new HashSet<CubeXVariable>();
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
	
	@Override
	public boolean isString() {
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(!(other instanceof CubeXString))
			return false;
		
		if(((CubeXString)other).isString()){
			CubeXString oS = (CubeXString)other;
			return value.equals(oS.value);
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
}
