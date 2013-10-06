package main.type;

public class CubeXTypeSpecial extends CubeXType 
{
	private boolean isThing;

	public CubeXTypeSpecial(boolean isThing)
	{
		this.isThing=isThing;
	}
	
	public boolean isThing()
	{
		return isThing;
	}
	public boolean isNothing()
	{
		return !isThing;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(isThing?"Thing":"Nothing");
		return sb.toString();
	}
}
