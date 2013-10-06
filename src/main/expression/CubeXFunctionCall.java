package main.expression;
import java.util.ArrayList;
import java.util.Arrays;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.type.CubeXType;


public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	private String name;
	private ArrayList<? extends CubeXType> parameters;
	private ArrayList<? extends CubeXExpression> args;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<? extends CubeXType> parameters, ArrayList<? extends CubeXExpression> args)
	{
		this.parent=parent;
		this.name=name;
		if(parameters==null)
		{
			parameters = new ArrayList<CubeXType>();
		}
		this.parameters=parameters;
		if(args==null)
		{
			args = new ArrayList<CubeXExpression>();
		}
		this.args=args;
	}
	
	public CubeXFunctionCall(CubeXExpression parent, String name, CubeXExpression arg)
	{
		this(parent, name, null, new ArrayList<CubeXExpression>(Arrays.asList(arg)));
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(parent.toString()).append(" . ").append(name).append(" < ");
		String prefix ="";
		for(CubeXType t : parameters)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		
		prefix ="";
		for(CubeXExpression expr : args)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append(")");
		return sb.toString();
	}

}
