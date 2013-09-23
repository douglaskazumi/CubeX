
public class CubeXThrough extends CubeXBinaryExpression {

	private boolean includeUpper;
	private boolean includeLower;
	
	public CubeXThrough(CubeXExpression a, CubeXExpression b, boolean includeLower, boolean includeUpper) 
	{
		super(a, b);
		this.includeLower=includeLower;
		this.includeUpper=includeUpper;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" . through < > ( ").append(b.toString()).append(" , ").append(includeLower).append(" , ").append(includeUpper).append(" )");
		return sb.toString();
	}
}
