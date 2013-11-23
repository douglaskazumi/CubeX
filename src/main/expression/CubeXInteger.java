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

public class CubeXInteger extends CubeXExpression 
{
	private int value;
	
	public CubeXInteger(int value)
	{
		this.value = value;
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) {
		return CubeXType.getInteger();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}
	
	@Override
	public HashSet<String> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		return new HashSet<String>();
	}

	@Override
	public CubeXExpression reduceBoxes() {
		return this;
	}
	
	@Override
	public CubeXExpression addBoxes() {
		return Boxer.boxify(this);
	}
	@Override
	public CubeXExpression simplifyFunctionBoxes()
	{
		return this;
	}
	
	@Override
	public CubeXExpression primitivifyVariables()
	{
		return this;
	}
	
	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean equals(CubeXExpression other) {
		if(other != null && other.isInteger()){
			CubeXInteger oI = (CubeXInteger)other;
			return value == oI.value;
		}

		return false;
	}
}