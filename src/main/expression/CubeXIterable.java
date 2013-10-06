package main.expression;
import java.util.ArrayList;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.type.CubeXType;

public class CubeXIterable extends CubeXExpression
{
	ArrayList<CubeXExpression> entries;
	
	public CubeXIterable(ArrayList<CubeXExpression> entries) 
	{
		this.entries = entries;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		String prefix="";
		for (CubeXExpression expr : entries)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon)
	{
		CubeXType curType=CubeXType.getNothing();
		for(CubeXExpression exp : entries)
		{
			curType = CubeXType.join(curType, exp.getType(classCon, funCon, varCon, typeVarCon), classCon, typeVarCon);
		}
		return new 
	}
}
