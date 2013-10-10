package main.type;

import main.context.ClassContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.TypeVarSubstitution;

public class CubeXTypeIntersection extends CubeXType {

	public CubeXType A;
	public CubeXType B;
	
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
	public Tuple<TypeVarSubstitution, CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException 
	{
		Tuple<TypeVarSubstitution, CubeXFunction> res = A.methodLookup(name, classCon);
		if(res.second==null)
		{
			res=B.methodLookup(name, classCon);
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
			constructableType=A.getConstructableComponent();
		return constructableType;
	}

}
