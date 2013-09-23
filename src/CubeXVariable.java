
public class CubeXVariable extends CubeXExpression 
{
	private String name;
	
	public CubeXVariable(String name)
	{
		this.name=name;
	}
	
	public String toString()
	{
		return name;
	}
}
