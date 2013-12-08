package main.yields;

import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.ExpressionContext;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.util.CubeXArgument;
import main.util.Tuple;

public class CubeXYieldStatement extends CubeXStatement 
{

	private CubeXExpression expr;
	
	public CubeXYieldStatement(CubeXExpression x)
	{
		expr=x;
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force,	ClassContext classCon, FunctionContext funCon,	VariableContext varCon, TypeVariableContext typeVarCon,	boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException,TypeCheckException {
		if(!isYielder)
			throw new TypeCheckException("Yielder in not yielding block");
		return new Tuple<Boolean, CubeXType>(true, expr.getType(force, classCon, funCon, varCon, typeVarCon, setField, par));
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("yield ").append(expr.toString()).append(";");
		return sb.toString();
	}
	
	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece next,	boolean isTopLevel) {
		return null;
	}

	@Override
	public void initializeUsedVariables(boolean onlyGlobals,
			HashSet<CubeXFunction> ignoredFunctions) {
	}

	@Override
	public void updateDeadVariables() {
	}

	@Override
	public void addBoxes() {
	}

	@Override
	public void simplifyFunctionBoxes() {
	}

	@Override
	public void primitivifyVariables() {
	}

	@Override
	public void reduceBoxes() {
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con)
			throws ContextException {
		return null;
	}

}
