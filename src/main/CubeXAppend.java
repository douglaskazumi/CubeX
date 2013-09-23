package main;

public class CubeXAppend extends CubeXBinaryExpression {

	public CubeXAppend(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" ++ ").append(b.toString());
		return sb.toString();
	}
	
}
