package main.program;

public class CubeXFunctionDeclaration 
{

	public String name;
	public CubeXScheme scheme;
	
	public CubeXFunctionDeclaration(String name, CubeXScheme scheme)
	{
		this.name=name;
		this.scheme=scheme;
	}
	
	public String toString()
	{
		return (new CubeXFunction(this)).toString();
	}
}
