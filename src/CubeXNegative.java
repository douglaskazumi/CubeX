
public class CubeXNegative extends CubeXUnaryExpression 
{

	public CubeXNegative(CubeXExpression a) 
	{
		super(a);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . negative < > ( )");
		return sb.toString();
	}
}
