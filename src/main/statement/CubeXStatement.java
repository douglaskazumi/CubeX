package main.statement;

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
}
