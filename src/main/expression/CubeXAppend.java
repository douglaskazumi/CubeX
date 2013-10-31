package main.expression;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
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
	protected CubeXType calculateType(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException {
		CubeXType typeA=a.getType(force, classCon,funCon,varCon,typeVarCon, setField, par);
		CubeXType typeB=b.getType(force, classCon,funCon,varCon,typeVarCon, setField, par);
		if (typeA.equals(CubeXType.getString()))
			typeA=new CubeXTypeIterable(CubeXType.getCharacter());
		if (typeB.equals(CubeXType.getString()))
			typeB=new CubeXTypeIterable(CubeXType.getCharacter());
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
	
