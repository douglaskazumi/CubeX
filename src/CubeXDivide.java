
public class CubeXDivide extends CubeXBinaryExpression {

	public CubeXDivide(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . divide < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
