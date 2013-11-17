package main.program;

import java.util.ArrayList;
import java.util.HashSet;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.util.Tuple;

public abstract class CubeXProgramPiece 
{
	public HashSet<String>  locals = new HashSet<String>();

	public abstract Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException;
	
	public boolean isFunction()
	{
		return false;
	}
	
	public boolean isStatement()
	{
		return false;
	}
	
	public boolean isClass()
	{
		return false;
	}

	public boolean isInterface()
	{
		return false;
	}
	
	

	public void addLocal(String name)
	{
		locals.add(name);
	}

	public abstract String preC();

	public abstract String toC();
	
	
	protected HashSet<String> currentOut = new HashSet<String>();
	protected HashSet<String> currentIn = new HashSet<String>();
	protected HashSet<String> definedVars = new HashSet<String>();
	protected HashSet<String> usedVars = new HashSet<String>();
	protected ArrayList<CubeXProgramPiece> succ = new ArrayList<CubeXProgramPiece>();
	protected ArrayList<CubeXProgramPiece> pred = new ArrayList<CubeXProgramPiece>();
	
	public abstract ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece next);
	public void addSucc(CubeXProgramPiece after)
	{
		
		if(after!=null)
		{
			this.succ.add(after);
			after.pred.add(this);
		}
	}
	
	public ArrayList<CubeXProgramPiece> getSuccessors()
	{
		return succ;
	}
	public ArrayList<CubeXProgramPiece> getPredecesors()
	{
		return pred;
	}
	
	protected boolean isUsedInitialized = false;
	public abstract void initializeUsedVariables();
	public HashSet<String> getUsedVariables()
	{
		if(!isUsedInitialized)
		{
			initializeUsedVariables();
		}
		
		return usedVars;
	}
	
	protected boolean isDefinedInitialized = false; 
	public void initializeDefinedVariables()
	{
		return;
	}
	public HashSet<String> getDefinedVariables()
	{
		if(!isDefinedInitialized)
		{
			initializeDefinedVariables();
		}
		return definedVars;
	}
	
	public HashSet<String> getInVariables()
	{
		return currentIn;
	}
	public HashSet<String> getOutVariables()
	{
		return currentOut;
	}	
}
