package main.type;

import java.util.ArrayList;

import main.context.FunctionContext;


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
	
}
