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

public class CubeXWhileStatement extends CubeXStatement 
{
	private CubeXExpression condition;
	private CubeXStatement whilestatement;
	
	public CubeXWhileStatement(CubeXExpression condition, CubeXStatement whilestatement)
	{
		this.condition = condition;
		this.whilestatement = whilestatement;
	}
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("while ( ").append(condition.toString()).append(" ) ").append(whilestatement.toString());
		return sb.toString();
	}


	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException 
	{
		CubeXType condType = condition.getType(classCon, funCon, varCon, typeVarCon);
		if(!condType.isBool())
			throw new TypeCheckException("While condition not a bool");
		boolean mutable = varCon.isMutable();
		whilestatement.typecheck(classCon, funCon, (VariableContext) varCon.createChildContext(), typeVarCon);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}
	
}
