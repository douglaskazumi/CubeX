package main;

public class CubeXOr extends CubeXBinaryExpression {

	public CubeXOr(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . or < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
