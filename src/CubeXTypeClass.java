import java.util.ArrayList;


public class CubeXTypeClass extends CubeXType 
{
	
	private String name;
	private ArrayList<CubeXType> parameters;
	
	public CubeXTypeClass(String name, ArrayList<CubeXType> parameters)
	{
		this.name=name;
		if(parameters==null)
			parameters=new ArrayList<CubeXType>();
		this.parameters = parameters;
	}
	
	public boolean isClass()
	{
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" < ");
		
		String prefix="";
		for(CubeXType p : parameters)
		{
			sb.append(prefix).append(p.toString()).append(" ");
			prefix=", ";
		}
		sb.append(">");
		return sb.toString();
	}
}

class CubeXTypeBoolean extends CubeXTypeClass
{

	public CubeXTypeBoolean()
	{
		super("Boolean",new ArrayList<CubeXType>());
	}
	
	public boolean isBool()
	{
		return true;
	}
	
}

class CubeXTypeInteger extends CubeXTypeClass
{
	public CubeXTypeInteger()
	{
		super("Integer", new ArrayList<CubeXType>());
	}
	
	public boolean isInt()
	{
		return true;
	}
}


class CubeXTypeCharacter extends CubeXTypeClass
{
	public CubeXTypeCharacter()
	{
		super("Character", new ArrayList<CubeXType>());
	}
	
	public boolean isChar()
	{
		return true;
	}
}