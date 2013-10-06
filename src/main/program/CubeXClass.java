package main.program;
import java.util.ArrayList;

import main.context.FunctionContext;
import main.expression.CubeXExpression;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXClass extends CubeXProgramPiece {

	private String name;
	private ArrayList<CubeXTypeVariable> types;
	private CubeXType parentType;
	private ArrayList<CubeXFunction> functions;
	private ArrayList<CubeXArgument> constructorArgs;
	private ArrayList<CubeXStatement> statements;
	private ArrayList<CubeXExpression> superArgs;
	
	private FunctionContext myFunctionContext;
	
	
	public CubeXClass(String name, ArrayList<CubeXTypeVariable> types, ArrayList<CubeXArgument> constructorArgs, CubeXType parentType, ArrayList<CubeXStatement> stats, ArrayList<CubeXExpression> superArgs, ArrayList<CubeXFunction> functions)
	{
		if(parentType==null)
			parentType=CubeXType.getThing();
		if(superArgs==null)
			superArgs=new ArrayList<CubeXExpression>();
		
		this.name=name;
		this.types=types;
		this.constructorArgs=constructorArgs;
		this.parentType=parentType;
		this.statements=stats;
		this.superArgs=superArgs;
		this.functions=functions;
		
		//TODO initialize function context somewhere!
	}
	
	public FunctionContext getFunctionContext()
	{
		return myFunctionContext;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("class ").append(name).append(" < ");
		String prefix ="";
		for(CubeXTypeVariable t : types)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		prefix="";
		for(CubeXArgument a : constructorArgs)
		{
			sb.append(prefix).append(a.toString()).append(" ");
			prefix=", ";
		}
		sb.append(") extends ").append(parentType.toString()).append(" { ");
		
		for(CubeXStatement stat : statements)
		{
			sb.append(stat).append(" ");
		}
		sb.append("super ( ");
		prefix="";
		for(CubeXExpression a : superArgs)
		{
			sb.append(prefix).append(a.toString());
			prefix=", ";
		}
		sb.append(") ;");
		for(CubeXFunction a : functions)
		{
			sb.append(" ").append(a.toString());
		}	
		
		sb.append(" }");
		return sb.toString();
	}
}
