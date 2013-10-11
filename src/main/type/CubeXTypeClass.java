package main.type;
import java.util.ArrayList;

import main.context.*;
import main.exceptions.ContextException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.util.Tuple;
import main.util.TypeVarSubstitution;


public class CubeXTypeClass extends CubeXTypeClassBase
{
	
	private CubeXClass classDeclaration;
		
	public CubeXTypeClass(String name, ArrayList<? extends CubeXType> parameters)
	{
		super(name, parameters);
	}
	
	public CubeXClass getDeclaration(ClassContext classCon) throws ContextException
	{
		if (classDeclaration==null)
		{
			CubeXClassBase base = classCon.lookup(name);
			
			if (base==null || !base.isClass())
			{
				throw new ContextException("Bad Class");
			}
			classDeclaration = (CubeXClass)base;
		}
			
		return classDeclaration;
	}
	
	public TypeVarSubstitution geTypeVarSub(ClassContext classCon) throws ContextException
	{
		return new TypeVarSubstitution(getDeclaration(classCon).getTypes(),parameters);
	}
	
	public CubeXType getConstructableComponent()
	{
		return this;
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

	@Override
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException
	{
		CubeXFunction fun = getDeclaration(classCon).getFunctionContext().lookup(name);
		if(fun==null)
			throw new ContextException();
		return new Tuple<TypeVarSubstitution, CubeXFunction>(geTypeVarSub(classCon),fun);
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


