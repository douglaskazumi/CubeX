package main.program;

import main.c.GlobalAwareness;
import main.expression.CubeXVariable;
import main.type.CubeXType;

public class CubeXArgument {

	public CubeXType type;
	public CubeXVariable variable;
	
	public CubeXArgument(CubeXVariable variable, CubeXType type)
	{
		this.type=type;
		this.variable=variable;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(variable.toString()).append(" : ").append(type.toString());
		return sb.toString();
	}
}
