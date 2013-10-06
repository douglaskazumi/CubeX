package main.program;
import java.util.ArrayList;

import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXScheme {

	
	public ArrayList<CubeXTypeVariable> types;
	public ArrayList<CubeXArgument> arglist;
	public CubeXType returnType;
	
	public CubeXScheme(ArrayList<CubeXTypeVariable> types, ArrayList<CubeXArgument> arglist, CubeXType returnType)
	{
		this.types = types;
		this.arglist = arglist;
		this.returnType = returnType;
	}

}
