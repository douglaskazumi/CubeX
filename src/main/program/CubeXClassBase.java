package main.program;

import java.util.ArrayList;

import main.c.VTable;
import main.context.FunctionContext;
import main.context.GlobalContexts;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.type.CubeXTypeClassBase;
import main.type.CubeXTypeVariable;

public abstract class CubeXClassBase extends CubeXProgramPiece {

	private static int curTypeID=5;
	
	protected int myTypeID=4;
	
	protected String name;
	protected ArrayList<CubeXTypeVariable> types;
	protected CubeXType parentType;
	protected ArrayList<CubeXFunction> functions;


	protected FunctionContext myFunctionContext;
	
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

	public boolean isClass()
	{
		return false;
	}

	public boolean isInterface()
	{
		return false;
	}

	public int getID()
	{
		return myTypeID;
	}
	
	public void setFunctions(ArrayList<CubeXFunction> funs)
	{
		this.functions=funs; 
	}
	
	public int getRequiredNumParameters()
	{
		return types.size();
	}
	
	public ArrayList<CubeXFunction> getFunctions() {
		return functions;
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
	
	public void populateVTable(VTable vTable, CubeXClass startingClass, boolean ignoreParents) throws ContextException, TypeCheckException
	{

		if(!ignoreParents)
		{
			ArrayList<CubeXType> supers = CubeXType.getSuperTypes(parentType, GlobalContexts.classContext);
			for(CubeXType supType : supers)
			{
				((CubeXClassBase)((CubeXTypeClassBase)supType).getDeclaration(GlobalContexts.classContext)).populateVTable(vTable, startingClass, true);
			}
		}

		for(CubeXFunction fun : startingClass.functions)
		{
			vTable.addEntry(fun);
		}
		vTable.finishBase(this);

	}
}
