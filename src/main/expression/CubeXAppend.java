package main.expression;


public class CubeXAppend extends CubeXExpression {

	private CubeXExpression a;
	private CubeXExpression b;
	
	public CubeXAppend(CubeXExpression a, CubeXExpression b) {
		this.a=a;
		this.b=b;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" ++ ").append(b.toString());
		return sb.toString();
	}
	
}
