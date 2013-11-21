package main.Optimizations;

import java.util.HashSet;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;

public class Boxer extends CubeXExpression {

	private CubeXExpression innerExpression;
	protected boolean isBoxed;
	
	public Boxer(CubeXExpression expr, boolean isBoxed)
	{
		innerExpression = expr;
		myType = innerExpression.getTypeUnsafe();
		this.isBoxed = isBoxed;
	}

	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon,
			FunctionContext funCon, VariableContext varCon,
			TypeVariableContext typeVarCon, boolean setField, CubeXClassBase par)
			throws ContextException, TypeCheckException {
		// TODO Auto-generated method stub
		return innerExpression.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return innerExpression.preC(par);
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		if(isBoxed)
		{
			sb.append("box((int)").append(innerExpression.toC(par)).append(innerExpression.getTypeUnsafe().isInt()?", false)":", true)");
		}
		else
		{
			sb.append("unbox((object_t *)").append(innerExpression.toC(par)).append(")");
		}

		this.temps.addAll(innerExpression.getTemps());
		return sb.toString();
	}

	@Override
	public HashSet<String> getUsedVars(boolean globals,
			HashSet<CubeXFunction> ignoredFunctions) {
		// TODO Auto-generated method stub
		return innerExpression.getUsedVars(globals, ignoredFunctions);
	}
	
	public boolean isBoxer()
	{
		return true;
	}
	
	public boolean isBoxed()
	{
		return isBoxed;
	}
	
	public boolean isBoolean()
	{
		return innerExpression.isBoolean();
	}

	@Override
	public CubeXExpression reduceBoxes() 
	{
		if(innerExpression.isBoxer())
		{
			if(((Boxer)innerExpression).isBoxed())
			{
				if(!this.isBoxed())
					return ((Boxer)innerExpression).innerExpression.reduceBoxes();
			}
			else
			{
				if(this.isBoxed())
					return ((Boxer)innerExpression).innerExpression.reduceBoxes();
			}
		}
		return this;
	}
	
	public String toString()
	{
		return (isBoxed?"":"un") + "box("+innerExpression.toString() +")";
	}

	@Override
	public CubeXExpression addBoxes()
	{
		innerExpression = innerExpression.addBoxes();
		return this;
	}

	
	public static CubeXExpression boxify(CubeXExpression expr)
	{
		return new Boxer(expr, true);	
	}
	public static CubeXExpression unboxify(CubeXExpression expr)
	{
		return new Boxer(expr, false);	
	}

	@Override
	public CubeXExpression simplifyFunctionBoxes() 
	{
		innerExpression = innerExpression.simplifyFunctionBoxes();
		return this;
	}
	
	@Override
	public CubeXExpression primitivifyVariables() 
	{
		innerExpression = innerExpression.primitivifyVariables();
		return this;
	}
}
