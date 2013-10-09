package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.type.Tuple;

public class CubeXForStatement extends CubeXStatement {

	private String variable;
	private CubeXExpression forexpression;
	private CubeXStatement forbody;
	
	public CubeXForStatement(String variable, CubeXExpression forexpression, CubeXStatement forbody)
	{
		this.variable = variable;
		this.forbody = forbody;
		this.forexpression = forexpression;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("for ( ").append(variable).append(" in ").append(forexpression.toString()).append(" ) ").append(forbody.toString());
		return sb.toString();
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException 
	{
		CubeXType forExprType = forexpression.getType(classCon, funCon, varCon, typeVarCon);
		if(!forExprType.isIterable())
			throw new TypeCheckException();
		CubeXTypeIterable forIterable = (CubeXTypeIterable)forExprType;
		CubeXType innerType=forIterable.getInnerType();
		
		boolean mutable = varCon.isMutable();
		varCon.setMutable(false);;
		VariableContext innerCon = (VariableContext)varCon.createChildContext();
		innerCon.add(variable, innerType);
		forbody.typecheck(classCon, funCon, (VariableContext) varCon.createChildContext(), typeVarCon);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}
	
}
