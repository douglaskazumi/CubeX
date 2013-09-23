package main;

public class CubeXLessThan extends CubeXBinaryExpression 
{
	private boolean strict;
	
	public CubeXLessThan(CubeXExpression a, CubeXExpression b, boolean strict)
	{
		super(a, b);
		this.strict=strict;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . lessThan < > ( ").append(b.toString()).append(" , ").append(strict).append(" )");
		return sb.toString();
	}
	
}
