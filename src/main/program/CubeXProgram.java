package main.program;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.context.*;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
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
		GlobalContexts.classContext = new ClassContext(null);
		GlobalContexts.functionContext = new FunctionContext(null);
		GlobalContexts.variableContext = new VariableContext(null);
		
		//TODO finish this
		ArrayList<CubeXClass> classes = new ArrayList<CubeXClass>();
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXClass newClass;
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		//Iterable
		newClass = new CubeXClass("Iterable", new ArrayList<CubeXTypeVariable>(Arrays.asList(new CubeXTypeVariable("E"))), null, CubeXType.getThing(), null, null, null);
		classes.add(newClass);
		
		//Boolean
		newScheme = new CubeXScheme(null, null, CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("negate",newScheme));
		functions.add(newFunction);
		
		newClass = new CubeXClass("Boolean", null, null, CubeXType.getThing(), null, null, functions);
		classes.add(newClass);
		
	}
	
	public boolean typeCheck()
	{
		initializeContexts();
		
		try
		{
			Iterator<CubeXProgramPiece> piecesIt = pieces.iterator();
			boolean wasFunction = false;
			ArrayList<CubeXFunction> curFunSet = new ArrayList<CubeXFunction>();
			while(piecesIt.hasNext())
			{
				CubeXProgramPiece piece = piecesIt.next();
				if(piece.isFunction())
				{
					curFunSet.add((CubeXFunction)piece);
					wasFunction = true;
					continue;
				}
				
				checkFunctionBlock(wasFunction, curFunSet);

				wasFunction=false;
			}
			
			//shouldn't need this
			checkFunctionBlock(wasFunction, curFunSet);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private void checkFunctionBlock(boolean wasFunction, ArrayList<CubeXFunction> curFunSet) throws ContextException, TypeCheckException
	{
		if(wasFunction)
		{
			for(CubeXFunction f : curFunSet)
			{
				GlobalContexts.functionContext.add(f.getName(), f);
			}
			for(CubeXFunction f : curFunSet)
			{
				f.typecheck(GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null));
			}
			curFunSet.clear();
		}

	}
	
}
