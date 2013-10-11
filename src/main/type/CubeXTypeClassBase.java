package main.type;

import java.util.ArrayList;
import java.util.Iterator;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.program.CubeXClassBase;

public abstract class CubeXTypeClassBase extends CubeXType 
{
	
	protected String name;
	protected ArrayList<? extends CubeXType> parameters;
	
	public CubeXTypeClassBase(String name, ArrayList<? extends CubeXType> parameters)
	{
		this.name=name;
		if(parameters==null)
			parameters=new ArrayList<CubeXType>();
		this.parameters = parameters;
	}
	
	public abstract CubeXClassBase getDeclaration(ClassContext classCon) throws ContextException;
	
	
	public boolean equals(CubeXType other)
	{
		if (other==null)
			return false;
		if(!other.isClass() || !other.isInterface())
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
