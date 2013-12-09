package main.statement;

import java.util.*;

import main.Optimizations.*;
import main.c.*;
import main.context.*;
import main.exceptions.*;
import main.expression.*;
import main.program.*;
import main.type.*;
import main.util.*;

public class CubeXYieldStatement extends CubeXStatement 
{

	private CubeXExpression expr;
	private int yieldId;
	
	public CubeXYieldStatement(CubeXExpression x)
	{
		expr=x;
		yieldId = GlobalAwareness.getNextYieldId();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		for (String var : par.locals.keySet()) {
			sb.append("yielder->var_").append(CUtils.canonName(var)).append(" = ").append(CUtils.canonName(var)).append(";").append(System.lineSeparator());
		}
		sb.append(System.lineSeparator()).append("yielder->status = ").append(yieldId).append(";").append(System.lineSeparator());
		sb.append("yielder->returnValue = ").append(expr.toC(this)).append(";").append(System.lineSeparator());
		sb.append("return yielder;").append(System.lineSeparator());
		sb.append("LABEL_").append(yieldId).append(":").append(System.lineSeparator());
		
		return sb.toString();
		
	}

	@Override
	public String preC(CubeXProgramPiece par)
	{
		if(par != null)
			((CubeXYielder)par).addYield(yieldId);
		
		return expr.preC(par);
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
