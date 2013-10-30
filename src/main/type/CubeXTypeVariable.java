package main.type;

import java.util.ArrayList;

import main.context.*;
import main.exceptions.*;
import main.program.*;
import main.util.*;

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
	
	@Override
	public boolean isExtendable()
	{
		return false;
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
	public Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException
	{
		throw new TypeCheckException();
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other==null)
			return false;
		if(other instanceof CubeXType)
			return equals((CubeXType)other);
		return false;
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

	@Override
	public ArrayList<CubeXFunction> getAllFunctions(ClassContext classCon) throws ContextException {
		return new ArrayList<CubeXFunction>();
	}
}
