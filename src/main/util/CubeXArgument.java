package main.util;

import main.expression.CubeXVariable;
import main.program.CubeXClass;
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
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXArgument))
			return false;
		
		CubeXArgument oA = (CubeXArgument) other;
		
		return type.equals(oA.type) && variable.equals(oA.variable);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((variable == null) ? 0 : variable.hashCode());
		return result;
	}
}
