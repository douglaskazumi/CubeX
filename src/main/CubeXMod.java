package main;

public class CubeXMod extends CubeXBinaryExpression {

	public CubeXMod(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . modulo < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
