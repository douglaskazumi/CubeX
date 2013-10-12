package main.type;
import java.util.ArrayList;

import main.context.*;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.util.Tuple;
import main.util.TypeVarSubstitution;


public class CubeXTypeClass extends CubeXTypeClassBase
{
	
	private CubeXClass classDeclaration;
		
	protected CubeXTypeClass(String name, ArrayList<? extends CubeXType> parameters)
	{
		super(name, parameters);
	}
	
	public CubeXTypeClassBase getActualType(ClassContext classCon) throws ContextException
	{
		CubeXClassBase base = classCon.lookup(name);
		if(base==null)
			throw new ContextException("Probably bad");
		if(base.isClass())
			return this;
		else
			return new CubeXTypeInterface(name, parameters);
	}
	
	public static CubeXTypeClass NewCubeXTypeClass(String name, ArrayList<? extends CubeXType> parameters) throws TypeCheckException
	{

		if(name.equals("Iterable"))
		{
			if(parameters==null || parameters.size()!=1)
				throw new TypeCheckException("can't make bad iterable");
			return new CubeXTypeIterable(parameters.get(0));
		}
		else if(name.equals("String"))
		{
			if(parameters!=null && parameters.size()!=0)
				throw new TypeCheckException("can't make bad string");
			return new CubeXTypeString();
		} 
		else if(name.equals("Boolean"))
		{
			if(parameters!=null && parameters.size()!=0)
				throw new TypeCheckException("can't make bad bool");
			return new CubeXTypeBoolean();
		}
		else if(name.equals("Integer"))
		{
			if(parameters!=null && parameters.size()!=0)
				throw new TypeCheckException("can't make bad integer");
			return new CubeXTypeInteger();
		}
		else if(name.equals("Character"))
		{
			if(parameters!=null && parameters.size()!=0)
				throw new TypeCheckException("can't make bad char");
			return new CubeXTypeCharacter();
		}
		else
		{
			return new CubeXTypeClass(name, parameters);
		}
		
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

	@Override
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException
	{
		CubeXFunction fun = getDeclaration(classCon).getFunctionContext().lookup(name);
		if(fun==null)
			throw new ContextException();
		return new Tuple<TypeVarSubstitution, CubeXFunction>(geTypeVarSub(classCon),fun);
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


