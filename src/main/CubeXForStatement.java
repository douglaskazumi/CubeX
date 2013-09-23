package main;

public class CubeXForStatement extends CubeXStatement {

	private String variable;
	private CubeXExpression forexpression;
	private CubeXStatement forbody;
	
	public CubeXForStatement(String variable, CubeXExpression forexpression, CubeXStatement forbody)
	{
		this.variable = variable;
		this.forbody = forbody;
		this.forexpression = forexpression;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("for ( ").append(variable).append(" in ").append(forexpression.toString()).append(" ) ").append(forbody.toString());
		return sb.toString();
	}
}
