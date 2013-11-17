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
		
}
