package main.statement;
import java.util.ArrayList;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.util.Tuple;


public class CubeXBlock extends CubeXStatement
{

	private ArrayList<CubeXStatement> innerStatements;
	
	public CubeXBlock()
	{
		innerStatements = new ArrayList<CubeXStatement>();
	}
	
	public void add(CubeXStatement stat)
	{
		if(stat.isBlock())
			innerStatements.addAll(((CubeXBlock)stat).innerStatements);
		else
			innerStatements.add(stat);
	}
	
	public boolean isBlock()
	{
		return true;
	}
	
	
	public CubeXStatement reduceBlock()
	{
		if(innerStatements.size()==0 || innerStatements.size()>1)
			return this;
		else
			return innerStatements.get(0);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(CubeXStatement stat : innerStatements)
		{
			sb.append(" ").append(stat.toString());
		}
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException 
	{
		boolean willReturn=false;
		CubeXType returnType=CubeXType.getNothing();
		for(CubeXStatement stat : innerStatements)
		{
			Tuple<Boolean, CubeXType> res = stat.typecheck(classCon, funCon, varCon, typeVarCon);
			if(res.first)
			{
				willReturn=true;
				CubeXType.join(res.second, returnType,classCon);
			}
		}
		
		if(willReturn==false)
			returnType=null;
		return new Tuple<Boolean, CubeXType>(willReturn, returnType);
	}
}
