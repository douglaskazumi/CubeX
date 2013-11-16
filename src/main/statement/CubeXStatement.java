package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

import main.program.CubeXProgramPiece;

public abstract class CubeXStatement extends CubeXProgramPiece
{
	public boolean isBlock()
	{
		return false;
	}
	
	public boolean isStatement()
	{
		return true;
	}
	
	public boolean isReturn()
	{
		return false;
	}
	
	@Override
	public final String toC()
	{
		return toC(null);
	}

	public abstract String toC(CubeXProgramPiece par);
	
	@Override
	public final String preC()
	{
		return preC(null);
	}

	public abstract String preC(CubeXProgramPiece par);
	
	protected HashSet<String> currentOut = new HashSet<String>();
	protected HashSet<String> currentIn = new HashSet<String>();
	protected HashSet<String> definedVars = new HashSet<String>();
	protected HashSet<String> usedVars = new HashSet<String>();
	protected ArrayList<CubeXStatement> succ = new ArrayList<CubeXStatement>();
	protected ArrayList<CubeXStatement> pred = new ArrayList<CubeXStatement>();
	
	public abstract void initializeSucc(CubeXStatement after);
	public void addSucc(CubeXStatement stat)
	{
		this.succ.add(stat);
		if(stat!=null)
			stat.pred.add(stat);
	}
	
	public ArrayList<CubeXStatement> getSuccessors()
	{
		return succ;
	}
	public ArrayList<CubeXStatement> getPredecesors()
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
