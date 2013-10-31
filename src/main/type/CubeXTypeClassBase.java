package main.type;

import java.util.ArrayList;
import java.util.Iterator;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.util.TypeVarSubstitution;

public abstract class CubeXTypeClassBase extends CubeXType 
{
	protected String name;
	public String getName() {
		return name;
	}

	protected ArrayList<? extends CubeXType> parameters;
	
	public CubeXTypeClassBase(String name, ArrayList<? extends CubeXType> parameters)
	{
		this.name=name;	
		if(parameters==null)
			parameters=new ArrayList<CubeXType>();
		this.parameters = parameters;
	}
	
	
	public static CubeXTypeClassBase NewCubeXTypeClassBase(String name, ArrayList<? extends CubeXType> parameters) throws TypeCheckException
	{
		return null;
	}
	
	public CubeXTypeClassBase getActualType(ClassContext classCon) throws ContextException
	{
		if(!isClass())
			return this;
		return ((CubeXTypeClass)this).getActualType(classCon);
	}
	
	public abstract CubeXClassBase getDeclaration(ClassContext classCon) throws ContextException;
	
	public ArrayList<CubeXFunction> getAllFunctions(ClassContext classCon) throws ContextException {
		return getDeclaration(classCon).getFunctions();
	}
	
	public TypeVarSubstitution getTypeVarSub(ClassContext classCon) throws ContextException
	{
		return new TypeVarSubstitution(getDeclaration(classCon).getTypes(),parameters);
	}
	
	public CubeXType getParent(ClassContext classCon) throws TypeCheckException, ContextException
	{
		return CubeXType.makeSubstitution(getDeclaration(classCon).getParentType(), getTypeVarSub(classCon));
	}
	
	public boolean equals(CubeXType other)
	{
		if (other==null)
			return false;
		if(!other.isClass() && !other.isInterface())
			return false;
		
		CubeXTypeClassBase otherBase = (CubeXTypeClassBase) other;
		if(!otherBase.name.equals(name))
			return false;
		
		if(parameters.size()!=otherBase.parameters.size())
			return false;
		
		Iterator<? extends CubeXType> myParaIt = parameters.iterator();
		Iterator<? extends CubeXType> otherParaIt = otherBase.parameters.iterator();
		
		while(myParaIt.hasNext())
		{
			CubeXType curMyType = myParaIt.next();
			CubeXType curOtherType = otherParaIt.next();
			
			if(!curMyType.equals(curOtherType))
				return false;
		}
		return true;
	}
	
	
}
