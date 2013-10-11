package main.type;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.Tuple;
import main.util.TypeVarSubstitution;

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
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException
	{
		throw new TypeCheckException();
	}
	
	@Override
	public boolean equals(CubeXType other)
	{
		if(other==null)
			return false;
		if(!other.isVariable())
			return false;
		return ((CubeXTypeVariable)other).name.equals(name);
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
}
