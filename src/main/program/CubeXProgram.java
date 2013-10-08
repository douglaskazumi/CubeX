package main.program;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import main.context.*;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;

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
		ArrayList<CubeXClass> classes = new ArrayList<CubeXClass>();
		ArrayList<CubeXFunctionDeclaration> functions = new ArrayList<CubeXFunctionDeclaration>();
		CubeXClass newClass;
		CubeXFunctionDeclaration newFunction;
		CubeXScheme newScheme;
		
		//Iterable
		newClass = new CubeXClass("Iterable", new ArrayList<CubeXTypeVariable>(Arrays.asList(new CubeXTypeVariable("E"))), null, CubeXType.getThing(), null, null, null);
		classes.add(newClass);
		
		newScheme = new CubeXScheme(null, null, CubeXType.getBoolean());
		newFunction = new CubeXFunctionDeclaration("negate",newScheme);
		functions.add(newFunction);
		
		newClass = new CubeXClass("Boolean", null, null, CubeXType.getThing(), null, null, null);
		classes.add(newClass);
		
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
