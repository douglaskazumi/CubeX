
public class CubeXEqual extends CubeXBinaryExpression {

	public CubeXEqual(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . equals < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
