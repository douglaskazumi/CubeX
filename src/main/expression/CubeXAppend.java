package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.*;


public class CubeXAppend extends CubeXExpression {

	private CubeXExpression a;
	private CubeXExpression b;
	
	public CubeXAppend(CubeXExpression a, CubeXExpression b) {
		this.a=a;
		this.b=b;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" ++ ").append(b.toString());
		return sb.toString();
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException, TypeCheckException {
		CubeXType typeA=a.getType(classCon,funCon,varCon,typeVarCon);
		CubeXType typeB=b.getType(classCon,funCon,varCon,typeVarCon);
		CubeXType joinedType=CubeXType.join(typeA, typeB, classCon);
		if (joinedType.isIterable())
		{
			return joinedType;
		}
		else
		{
			throw new TypeCheckException("Bad append types - not iterables");
		}
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		sb.append("iterableAppend(").append(a.toC()).append(", ").append(b.toC()).append(")");
		return sb.toString();
	}

	@Override
	public String preC() {
		StringBuilder sb = new StringBuilder();
		sb.append(a.preC()).append(b.preC());
		return sb.toString();
	}
	

	
}
