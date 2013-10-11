package main.type;


import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.Tuple;
import main.util.TypeVarSubstitution;

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
		if(this.isThing() && other.isThing())
			return true;
		if(this.isNothing() && other.isNothing())
			return true;
		
		return false;
	}
	
	
	
}
