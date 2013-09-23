
public class CubeXMinus extends CubeXBinaryExpression {

	public CubeXMinus(CubeXExpression a, CubeXExpression b) {
		super(a, b);
	}

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . minus < > ( ").append(b.toString()).append(" )");
		return sb.toString();
	}
}
