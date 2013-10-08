package main.statement;

import main.expression.CubeXExpression;

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
	
}