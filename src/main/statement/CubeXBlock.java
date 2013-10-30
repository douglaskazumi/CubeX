package main.statement;
import java.util.ArrayList;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
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
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		boolean willReturn=false;
		CubeXType returnType=CubeXType.getNothing();
		for(CubeXStatement stat : innerStatements)
		{
			Tuple<Boolean, CubeXType> res = stat.typecheck(force, classCon, funCon, varCon, typeVarCon, setField, par);
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

	@Override
	public String toC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String preC() {
		// TODO Auto-generated method stub
		return null;
	}
}
