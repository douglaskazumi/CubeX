package main.type;

import java.util.ArrayList;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.Triple;
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
	
	public CubeXType getConstructableComponent()
	{
		if (constructableType==null)
			constructableType=left.getConstructableComponent();
		return constructableType;
	}

	@Override
	public ArrayList<CubeXFunction> getAllFunctions(ClassContext classCon) throws ContextException {
		ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();
		funs.addAll(left.getAllFunctions(classCon));
		funs.addAll(right.getAllFunctions(classCon));
		return funs;
	}

	@Override
	public Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException 
	{
		try
		{
			Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res = left.methodLookup(name, classCon);
			return res;
		}
		catch(ContextException e)
		{
			try
			{
				Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res = right.methodLookup(name, classCon);
				return res;
			}
			catch(ContextException ee)
			{
			}
		}
		throw new ContextException("Not found in either intersection");
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

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(left.toString()).append(" & ").append(right.toString());
		return sb.toString();
	}

}
