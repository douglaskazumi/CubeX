package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.type.Tuple;

public class CubeXAssignment extends CubeXStatement {

	private String name;
	private CubeXExpression expr;
	
	public CubeXAssignment(String name,CubeXExpression expr)
	{
		this.name=name;
		this.expr=expr;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" := ").append(expr.toString()).append(" ;");
		return sb.toString();
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException {
		CubeXType type = expr.getType(classCon, funCon, varCon, typeVarCon);
		if(type.isVariable() && !(typeVarCon.lookup(((CubeXTypeVariable)type).getName())==null))
			throw new ContextException();
		
		varCon.add(name, type);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}
	
}
