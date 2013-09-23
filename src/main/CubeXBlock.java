package main;
import java.util.ArrayList;


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
}
