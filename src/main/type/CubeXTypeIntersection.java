package main.type;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.Tuple;
import main.util.TypeVarSubstitution;

public class CubeXTypeIntersection extends CubeXType {

	public CubeXType left;
	public CubeXType right;
	
	CubeXType constructableType;
	
	public CubeXTypeIntersection(CubeXType A, CubeXType B) 
	{
		this.left=A;
		this.right=B;
	}
	
	public boolean isIntersection()
	{
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(left.toString()).append(" & ").append(right.toString());
		return sb.toString();
	}

	@Override
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException 
	{
		Tuple<TypeVarSubstitution, CubeXFunction> res = left.methodLookup(name, classCon);
		if(res.second==null)
		{
			res=right.methodLookup(name, classCon);
			if(res.second==null)
			{
				throw new TypeCheckException();
			}
		}		
		return res;
	}
	
	public CubeXType getConstructableComponent()
	{
		if (constructableType==null)
			constructableType=left.getConstructableComponent();
		return constructableType;
	}

	@Override
	public boolean equals(CubeXType other)
	{
		if(other==null)
			return false;
		if(!other.isIntersection())
			return false;

		CubeXTypeIntersection otherIntersection = (CubeXTypeIntersection)other;
		
		if((left.equals(otherIntersection.left)&&right.equals(otherIntersection.right)) || (left.equals(otherIntersection.right)&&right.equals(otherIntersection.left)))
			return true;
		return false;
	}

}
