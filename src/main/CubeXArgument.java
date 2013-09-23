package main;

public class CubeXArgument {

	private CubeXType type;
	private CubeXVariable variable;
	
	public CubeXArgument(CubeXVariable variable, CubeXType type)
	{
		this.type=type;
		this.variable=variable;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(variable.toString()).append(" : ").append(type.toString());
		return sb.toString();
	}

}
