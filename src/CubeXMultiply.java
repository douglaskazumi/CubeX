
public class CubeXMultiply extends CubeXBinaryExpression {

	public CubeXMultiply(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . times < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
