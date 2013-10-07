package main.type;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;

public class CubeXTypeIntersection extends CubeXType {

	private CubeXType A;
	private CubeXType B;
	
	CubeXType constructableType;
	
	public CubeXTypeIntersection(CubeXType A, CubeXType B) 
	{
		this.A=A;
		this.B=B;
	}
	
	public boolean isIntersection()
	{
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(A.toString()).append(" & ").append(B.toString());
		return sb.toString();
	}

	@Override
	public CubeXFunction methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException 
	{
		CubeXType constructable = getConstructableComponent();
		if (constructable.isThing())
			throw new TypeCheckException();
		CubeXTypeClass constructableClass = (CubeXTypeClass)constructable;
		return constructableClass.methodLookup(name, classCon);
		
	}
	
	public CubeXType getConstructableComponent()
	{
		if (constructableType==null)
			constructableType=A.getConstructableComponent();
		return constructableType;
	}

}
