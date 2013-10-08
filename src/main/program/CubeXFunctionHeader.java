package main.program;

public class CubeXFunctionHeader 
{

	public String name;
	public CubeXScheme scheme;
	
	public CubeXFunctionHeader(String name, CubeXScheme scheme)
	{
		this.name=name;
		this.scheme=scheme;
	}
	
	public String toString()
	{
		return (new CubeXFunction(this)).toString();
	}
}
