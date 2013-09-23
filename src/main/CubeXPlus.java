package main;

public class CubeXPlus extends CubeXBinaryExpression {

	public CubeXPlus(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . plus < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}

