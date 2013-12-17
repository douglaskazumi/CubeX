package main.expression;

import java.util.HashSet;

import main.Optimizations.Boxer;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.CubeXCompiler;

public class CubeXBoolean extends CubeXExpression 
{
	private boolean value;
	
	public CubeXBoolean(boolean value)
	{
		this.value = value;
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) {
		return CubeXType.getBoolean();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		if(CubeXCompiler.optimizations)
			sb.append(value);
		else
			sb.append("createBoolean(").append(value).append(", 0)");
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}

	public boolean isBoolean()
	{
		return true;
	}

	public boolean getValue()
	{
		return value;
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
		return new Boxer(this, true);
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
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXBoolean))
			return false;
		
		if(((CubeXBoolean) other).isBoolean()){
			CubeXBoolean oB = (CubeXBoolean)other;
			return value == oB.value;
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		return value ? 1231 : 1237;
	}
}