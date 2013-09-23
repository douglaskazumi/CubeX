package main;

public class CubeXTypeVariable extends CubeXType {

	String name;
	
	public CubeXTypeVariable(String name)
	{
		this.name=name;
	}
	
	public boolean isVariable()
	{
		return true;
	}
	
	public String toString()
	{
		return name;
	}
}
