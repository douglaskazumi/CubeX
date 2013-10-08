package main.util;

import java.util.ArrayList;
import java.util.HashMap;

import main.type.*;

public class TypeVarSubstitution
{

	public HashMap<CubeXTypeVariable, CubeXType> mapping;
	
	//precondition that they have the same length
	public TypeVarSubstitution(ArrayList<CubeXTypeVariable> vars, ArrayList<? extends CubeXType> parameters)
	{
		mapping=new HashMap<CubeXTypeVariable, CubeXType>();
		
		for(int i=0; i<vars.size();++i)
		{
			mapping.put(vars.get(i), parameters.get(i));
		}
	}
	
	public CubeXType get(CubeXTypeVariable var)
	{
		return mapping.get(var);
	}

}
