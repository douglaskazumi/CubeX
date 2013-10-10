package main.type;

import java.util.ArrayList;

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
}
