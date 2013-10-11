package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXReturnStatement extends CubeXStatement {

	private CubeXExpression returnValue;
	
	public CubeXReturnStatement(CubeXExpression returnValue)
	{
		this.returnValue = returnValue;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("return ").append(returnValue.toString()).append(" ;");
		return sb.toString();
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon, FunctionContext funCon,	VariableContext varCon, TypeVariableContext typeVarCon)	throws ContextException, TypeCheckException {
		CubeXType type = returnValue.getType(classCon, funCon, varCon, typeVarCon);
		return new Tuple<Boolean, CubeXType>(true, type);
	}

}
