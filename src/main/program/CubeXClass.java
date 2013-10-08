package main.program;
import java.util.ArrayList;

import main.context.FunctionContext;
import main.expression.CubeXExpression;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXClass extends CubeXClassBase {


	private ArrayList<CubeXArgument> constructorArgs;
	private ArrayList<CubeXStatement> statements;
	private ArrayList<CubeXExpression> superArgs;
	

	private FunctionContext myFunctionContext;
	
	public FunctionContext getFunctionContext()
	{
		return myFunctionContext;
	}
	
	public boolean isClass()
	{
		return true;
	}
	
	
	public CubeXClass(String name, ArrayList<CubeXTypeVariable> types, ArrayList<CubeXArgument> constructorArgs, CubeXType parentType, ArrayList<CubeXStatement> stats, ArrayList<CubeXExpression> superArgs, ArrayList<CubeXFunction> functions)
	{
		super(name, types, parentType, functions);
	
		if(superArgs==null)
			superArgs=new ArrayList<CubeXExpression>();
		
		this.constructorArgs=constructorArgs;
		this.statements=stats;
		this.superArgs=superArgs;
		
		//TODO initialize function context somewhere!
	}
	

	public ArrayList<CubeXArgument> getConstructorArgs() {
		return constructorArgs;
	}

	public ArrayList<CubeXStatement> getStatements() {
		return statements;
	}

	public ArrayList<CubeXExpression> getSuperArgs() {
		return superArgs;
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
