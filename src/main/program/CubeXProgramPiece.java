package main.program;

import java.util.ArrayList;
import java.util.HashSet;

import main.c.CUtils;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.util.Tuple;

public abstract class CubeXProgramPiece 
{
	public ArrayList<String> gcAfter = new ArrayList<>();
	private boolean isTopLevel = false;
	public HashSet<String>  locals = new HashSet<String>();

	public abstract Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException;
	
	public boolean isTopLevel()
	{
		return this.isTopLevel;
	}
	public void setTopLevel(boolean isTop)
	{
		isTopLevel=isTop;
	}
	
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
	
	
	protected HashSet<String> currentDead;
	protected HashSet<String> currentOut = new HashSet<String>();
	protected HashSet<String> currentIn = new HashSet<String>();
	protected HashSet<String> definedVars = new HashSet<String>();
	protected HashSet<String> usedVarsGlobals = new HashSet<String>();
	protected HashSet<String> usedVarsAll = new HashSet<String>();
	protected ArrayList<CubeXProgramPiece> succ = new ArrayList<CubeXProgramPiece>();
	protected ArrayList<CubeXProgramPiece> pred = new ArrayList<CubeXProgramPiece>();
	
	public abstract ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece next, boolean isTopLevel);
	public void addSucc(CubeXProgramPiece after, boolean isTop)
	{
		this.isTopLevel=isTop;
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
	
	protected boolean isUsedInitializedGlobals = false;
	protected boolean isUsedInitializedAll = false;
	public abstract void initializeUsedVariables(boolean onlyGlobals, HashSet<CubeXFunction> ignoredFunctions);
	public HashSet<String> getUsedVariables(boolean onlyGlobals, HashSet<CubeXFunction> ignoredFunctions)
	{
		if(onlyGlobals && !isUsedInitializedGlobals)
		{
			initializeUsedVariables(onlyGlobals, ignoredFunctions);
			isUsedInitializedGlobals=true;
		}
		else if(!onlyGlobals && !isUsedInitializedAll)
		{
			initializeUsedVariables(onlyGlobals, ignoredFunctions);
			isUsedInitializedAll=true;
		}
		
		if(onlyGlobals)
		{
			return usedVarsGlobals;
		}
		else
		{
			return usedVarsAll;
		}
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
			isDefinedInitialized=true;
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
	
	public HashSet<String> getAllVariables()
	{
		HashSet<String> allVars = new HashSet<>();
		allVars.addAll(this.getUsedVariables(true, new HashSet<CubeXFunction>()));
		allVars.addAll(this.getDefinedVariables());
		return allVars;
		
	}
	
	public boolean doLiveVariableAnalysisStep(boolean onlyGlobals)
	{
		boolean didChange = false;
		for(CubeXProgramPiece s : this.succ)
		{
			didChange = didChange | currentOut.addAll(s.currentIn);
		}
		
		didChange = didChange | currentIn.addAll(this.getUsedVariables(onlyGlobals, new HashSet<CubeXFunction>()));
		@SuppressWarnings("unchecked")
		HashSet<String> temp = (HashSet<String>)currentOut.clone();
		temp.removeAll(this.getDefinedVariables());
		didChange = didChange | currentIn.addAll(temp);
				
		return didChange;
	}
	
	public abstract void updateDeadVariables();
	
	protected void setDeadVariables()
	{
		currentDead=getAllVariables();
		currentDead.removeAll(currentOut);
		gcAfter.clear();
		gcAfter.addAll(currentDead);
	}
	
	public String gcDeadVariables()
	{
		StringBuilder sb = new StringBuilder();

		for(String var : gcAfter)
		{
			sb.append("gc(gc_dec(").append(CUtils.canonName(var)).append("));\n");
			sb.append(CUtils.canonName(var)).append(" = NULL;\n");
		}
		return sb.toString();
	}

	public CubeXProgramPiece flatten(){
		return this;
	}
}
