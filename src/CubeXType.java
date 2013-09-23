
public abstract class CubeXType 
{

	public static CubeXTypeBoolean CubeXBool = new CubeXTypeBoolean();
	public static CubeXTypeBoolean getBoolean()
	{
		return CubeXBool;
	}
	
	public static CubeXTypeInteger CubeXInt = new CubeXTypeInteger();
	public static CubeXTypeInteger getInteger()
	{
		return CubeXInt;
	}
	
	public static CubeXTypeCharacter CubeXChar = new CubeXTypeCharacter();
	public static CubeXTypeCharacter getCharacter()
	{
		return CubeXChar;
	}	
	
	public static CubeXTypeSpecial CubeXThing = new CubeXTypeSpecial(true);
	public static CubeXTypeSpecial getThing()
	{
		return CubeXThing;
	}	
	
	public static CubeXTypeSpecial CubeXNothing = new CubeXTypeSpecial(false);
	public static CubeXTypeSpecial getNothing()
	{
		return CubeXNothing;
	}	
	
	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		return ((CubeXType)other).equals(this);
	}	
	
	
	public boolean isBool()
	{
		return false;
	}
	public boolean isInt()
	{
		return false;
	}
	public boolean isChar()
	{
		return false;
	}
	public boolean isClass()
	{
		return false;
	}
	public boolean isIntersection()
	{
		return false;
	}
	public boolean isThing()
	{
		return false;
	}
	public boolean isNothing()
	{
		return false;
	}
	public boolean isVariable()
	{
		return false;
	}
}

