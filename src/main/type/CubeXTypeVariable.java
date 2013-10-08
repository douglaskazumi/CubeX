package main.type;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;

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
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}

	@Override
	public CubeXFunction methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException
	{
		throw new TypeCheckException();
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other==null)
			return false;
		CubeXType otherType = (CubeXType)other;
		if(!otherType.isVariable())
			return false;
		return ((CubeXTypeVariable)other).name.equals(name);
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
}