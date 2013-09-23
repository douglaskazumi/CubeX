package main;

public class CubeXNegate extends CubeXUnaryExpression 
{

	public CubeXNegate(CubeXExpression a) 
	{
		super(a);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . negate < > ( )");
		return sb.toString();
	}
}
