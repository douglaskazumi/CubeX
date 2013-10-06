package main.program;
import java.util.ArrayList;

import main.context.*;

public class CubeXProgram {
	
	private ArrayList<CubeXProgramPiece> pieces;

	public CubeXProgram()
	{
		pieces = new ArrayList<CubeXProgramPiece>();
	}
		
	public void addPiece(CubeXProgramPiece piece)
	{
		pieces.add(piece);
	}
	
	public void addPieces(CubeXProgram program)
	{
		pieces.addAll(program.pieces);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		String prefix="";
		for(CubeXProgramPiece piece : pieces)
		{
			sb.append(prefix).append(piece.toString());
			prefix=" ";
		}
		
		return sb.toString();
	}

	private void initializeContexts()
	{
		GlobalContexts.classContext=new ClassContext(null);
		GlobalContexts.functionContext =new FunctionContext(null);
		GlobalContexts.variableContext=new VariableContext(null);
		
		//TODO finish this
		
		
	}
	
	public boolean typeCheck()
	{
		initializeContexts();
		
		try
		{
			for(CubeXProgramPiece piece : pieces )
			{
				//piece.typecheck();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
