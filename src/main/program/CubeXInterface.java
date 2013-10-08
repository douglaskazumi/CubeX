package main.program;
import java.util.ArrayList;

import main.context.BaseContext;
import main.context.FunctionContext;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXInterface extends CubeXClassBase {

	
	public boolean isInterface()
	{
		return true;
	}
	
	
	public CubeXInterface(String name, ArrayList<CubeXTypeVariable> types, CubeXType parentType, ArrayList<CubeXFunction> functions)
	{
		super(name, types,parentType,functions);
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
		
		for(CubeXFunction a : functions)
		{
			sb.append(" ").append(a.toString());
		}	
		sb.append(" }");
		return sb.toString();
	}

}
