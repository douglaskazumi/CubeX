import java.util.ArrayList;


public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	private String name;
	private ArrayList<? extends CubeXType> parameters;
	private ArrayList<? extends CubeXExpression> args;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<? extends CubeXType> parameters, ArrayList<? extends CubeXExpression> args)
	{
		this.parent=parent;
		this.name=name;
		if(parameters==null)
		{
			parameters = new ArrayList<CubeXType>();
		}
		this.parameters=parameters;
		if(args==null)
		{
			args = new ArrayList<CubeXExpression>();
		}
		this.args=args;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(parent.toString()).append(" . ").append(name).append(" < ");
		String prefix ="";
		for(CubeXType t : parameters)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		
		prefix ="";
		for(CubeXExpression expr : args)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append(")");
		return sb.toString();
	}
}
