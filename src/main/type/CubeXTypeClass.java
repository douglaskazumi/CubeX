package main.type;
import java.util.ArrayList;
import java.util.Arrays;

import main.context.*;
import main.exceptions.ContextException;
import main.program.CubeXClass;
import main.program.CubeXFunction;


public class CubeXTypeClass extends CubeXType 
{
	
	private String name;
	private ArrayList<CubeXType> parameters;
	private CubeXClass classDeclaration;
	
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
	
	public CubeXClass getClassDecl(ClassContext classCon) throws ContextException
	{
		if (classDeclaration==null)
			classDeclaration=classCon.lookup(name);
		return classDeclaration;
	}
	
	public CubeXType getConstructableComponent()
	{
		return this;
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

	@Override
	public CubeXFunction methodLookup(String name, ClassContext classCon) throws ContextException
	{
		return getClassDecl(classCon).getFunctionContext().lookup(name);
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
	
	public boolean isExtendable()
	{
		return false;
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
	public boolean isExtendable()
	{
		return false;
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
	public boolean isExtendable()
	{
		return false;
	}
}


class CubeXTypeString extends CubeXTypeClass
{

	public CubeXTypeString()
	{
		super("String", new ArrayList<CubeXType>(Arrays.asList(CubeXType.getCharacter())));
	}
	
}