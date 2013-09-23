package main;

public class CubeXAnd extends CubeXBinaryExpression {

	public CubeXAnd(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . and < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}

}
