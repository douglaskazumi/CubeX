package main.program;
import java.util.ArrayList;

import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXInterface extends CubeXProgramPiece {

	String name;
	ArrayList<CubeXTypeVariable> types;
	CubeXType parentType;
	ArrayList<CubeXFunction> declarations;
	
	
	
	public CubeXInterface(String name, ArrayList<CubeXTypeVariable> types, CubeXType parentType, ArrayList<CubeXFunction> declarations)
	{
		if(parentType==null)
			parentType=CubeXType.getThing();
		
		this.name=name;
		this.types=types;
		this.parentType=parentType;
		this.declarations=declarations;
	}

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("interface ").append(name).append(" < ");
		String prefix ="";
		for(CubeXTypeVariable t : types)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> extends ").append(parentType.toString()).append(" {");
		
		for(CubeXFunction a : declarations)
		{
			sb.append(" ").append(a.toString());
		}	
		sb.append(" }");
		return sb.toString();
	}
	
}
