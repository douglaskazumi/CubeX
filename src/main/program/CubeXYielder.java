package main.program;

import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.ExpressionContext;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXYielder extends CubeXProgramPiece {
	
	private CubeXStatement stat;
	
	private ArrayList<Integer> internalYields = new ArrayList<>();

	public void addYield(Integer yieldId)
	{
		internalYields.add(yieldId);
	}
	
	public CubeXYielder(CubeXStatement stat)
	{
		this.stat=stat;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException, TypeCheckException
	{
		return stat.typecheck(force, classCon, funCon, varCon, typeVarCon, setField, par, true);
	}

	@Override
	public String preC() {
		return null;
	}

	@Override
	public String toC() {
		return null;
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece next,
			boolean isTopLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeUsedVariables(boolean onlyGlobals,
			HashSet<CubeXFunction> ignoredFunctions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeadVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBoxes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void simplifyFunctionBoxes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void primitivifyVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduceBoxes() {
		
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con)
			throws ContextException {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append("yielder ").append(stat==null?";":stat.toString());
		return sb.toString();
	}
	
}
