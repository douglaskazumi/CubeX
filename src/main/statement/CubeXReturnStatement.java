package main.statement;

import main.expression.CubeXExpression;

public class CubeXReturnStatement extends CubeXStatement {

	private CubeXExpression returnValue;
	
	public CubeXReturnStatement(CubeXExpression returnValue)
	{
		this.returnValue = returnValue;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("return ").append(returnValue.toString()).append(" ;");
		return sb.toString();
	}

}
