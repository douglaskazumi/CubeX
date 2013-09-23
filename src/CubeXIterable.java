import java.util.ArrayList;

public class CubeXIterable extends CubeXExpression
{
	ArrayList<CubeXExpression> entries;
	
	public CubeXIterable(ArrayList<CubeXExpression> entries) 
	{
		this.entries = entries;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		String prefix="";
		for (CubeXExpression expr : entries)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append("]");
		return sb.toString();
	}
}
