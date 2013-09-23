package main;

public class CubeXIfStatement extends CubeXStatement {

	private CubeXExpression condition;
	private CubeXStatement ifstatement;
	private CubeXStatement elsestatement;
	
	public CubeXIfStatement(CubeXExpression condition, CubeXStatement ifstatement, CubeXStatement elsestatement)
	{
		this.condition = condition;
		this.ifstatement = ifstatement;
		this.elsestatement = elsestatement;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("if ( ").append(condition.toString()).append(" ) ").append(ifstatement.toString()).append(" else ");
		if(elsestatement!=null)
		{
			sb.append(elsestatement.toString());
		}
		else
		{
			sb.append("{ }");
		}
		
		return sb.toString();
	}
}
