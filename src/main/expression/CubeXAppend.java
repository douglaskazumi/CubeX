package main.expression;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
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
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws TypeCheckError, ContextException {
		CubeXType typeA=a.getType(classCon,funCon,varCon,typeVarCon);
		CubeXType typeB=b.getType(classCon,funCon,varCon,typeVarCon);
		CubeXType joinedType=CubeXType.join(typeA, typeB, classCon, typeVarCon);
		if (joinedType.isIterable())
		{
			return joinedType;
		}
		else
		{
			throw new TypeCheckError("Bad append types", null);
		}
	}
	
}
