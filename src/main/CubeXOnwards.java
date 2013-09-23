package main;

public class CubeXOnwards extends CubeXUnaryExpression 
{

	private boolean inclusive;
	
	public CubeXOnwards(CubeXExpression a, boolean inclusive) 
	{
		super(a);
		this.inclusive=inclusive;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . onwards < > ( ").append(inclusive).append(" )");
		return sb.toString();
	}
}
