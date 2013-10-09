package main.type;

import java.util.ArrayList;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXInterface;
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
		CubeXFunction fun = getInterfaceDecl(classCon).getFunctionContext().lookup(name);
		if(fun==null)
			throw new ContextException();
		return new Tuple<TypeVarSubstitution, CubeXFunction>(geTypeVarSub(classCon),fun);
	}


	private CubeXInterface getInterfaceDecl(ClassContext classCon) throws ContextException
	{
		if (interfaceDeclaration==null)
		{
			CubeXClassBase base = classCon.lookup(name);
			
			if (base==null || !base.isInterface())
			{
				throw new ContextException("Bad Class");
			}
			interfaceDeclaration = (CubeXInterface)base;
		}
			
		return interfaceDeclaration;
		
	}
	public TypeVarSubstitution geTypeVarSub(ClassContext classCon) throws ContextException
	{
		return new TypeVarSubstitution(getInterfaceDecl(classCon).getTypes(),parameters);
	}
}