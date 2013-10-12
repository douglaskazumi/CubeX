package main.type;

import java.util.ArrayList;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXInterface;
import main.util.Tuple;
import main.util.TypeVarSubstitution;

public class CubeXTypeInterface extends CubeXTypeClassBase 
{
	private CubeXInterface interfaceDeclaration;
	
	public CubeXTypeInterface(String name, ArrayList<? extends CubeXType> parameters)
	{
		super(name, parameters);
	}
	
	@Override
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException
	{
		CubeXFunction fun = getDeclaration(classCon).getFunctionContext().lookup(name);
		if(fun==null)
			throw new ContextException();
		return new Tuple<TypeVarSubstitution, CubeXFunction>(geTypeVarSub(classCon),fun);
	}

	@Override
	public CubeXType getConstructableComponent()
	{
		return CubeXType.getThing();
	}

	public boolean isInterface()
	{
		return true;
	}
	
	public CubeXInterface getDeclaration(ClassContext classCon) throws ContextException
	{
		if (interfaceDeclaration==null)
		{
			CubeXClassBase base = classCon.lookup(name);
			
			if (base==null || !base.isInterface())
			{
				throw new ContextException("Bad Interface");
			}
			interfaceDeclaration = (CubeXInterface)base;
		}
			
		return interfaceDeclaration;
		
	}
	public TypeVarSubstitution geTypeVarSub(ClassContext classCon) throws ContextException
	{
		return new TypeVarSubstitution(getDeclaration(classCon).getTypes(),parameters);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("INTERFACE < ");
		
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
