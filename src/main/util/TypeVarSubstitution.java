package main.util;

import java.util.ArrayList;
import java.util.HashMap;

import main.type.*;

public class TypeVarSubstitution
{

	HashMap<CubeXTypeVariable, CubeXType> mapping;
	
	//precondition that they have the same length
	public TypeVarSubstitution(ArrayList<CubeXTypeVariable> vars, ArrayList<CubeXType> vals)
	{
		mapping=new HashMap<CubeXTypeVariable, CubeXType>();
		
		for(int i=0; i<vars.size();++i)
		{
			mapping.put(vars.get(i), vals.get(i));
		}
	}
	
	public TypeVar

}
