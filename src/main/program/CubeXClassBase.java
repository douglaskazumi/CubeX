package main.program;

import java.util.ArrayList;

import main.context.FunctionContext;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;

public abstract class CubeXClassBase extends CubeXProgramPiece {

	private static int curTypeID=5;
	
	protected int myTypeID;
	
	protected String name;
	protected ArrayList<CubeXTypeVariable> types;
	protected CubeXType parentType;
	protected ArrayList<CubeXFunction> functions;


	protected FunctionContext myFunctionContext;
	
	public int getID()
	{
		return myTypeID;
	}
	
	public CubeXClassBase(String name, ArrayList<CubeXTypeVariable> types, CubeXType parentType, ArrayList<CubeXFunction> functions)
	{
		if(parentType==null)
			parentType=CubeXType.getThing();
		if(types==null)
			types = new ArrayList<CubeXTypeVariable>();
		if(functions==null)
			functions=new ArrayList<CubeXFunction>();
		
		this.name = name;
		this.types = types;
		this.parentType = parentType;
		this.functions = functions;
		this.myTypeID=curTypeID;
		curTypeID+=1;
	}

	public ArrayList<CubeXFunction> getFunctions() {
		return functions;
	}

	
	public boolean isClass()
	{
		return false;
	}
	
	public boolean isInterface()
	{
		return false;
	}
	
	public String getName() {
		return name;
	}


	public ArrayList<CubeXTypeVariable> getTypes() {
		return types;
	}


	public CubeXType getParentType() {
		return parentType;
	}


	public ArrayList<CubeXFunction> getDeclarations() {
		return functions;
	}
	
	
	public FunctionContext getFunctionContext()
	{
		return myFunctionContext;
	}


}
