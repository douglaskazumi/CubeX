package main.program;
import java.util.ArrayList;

import main.expression.CubeXExpression;
import main.statement.CubeXReturnStatement;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;


public class CubeXFunction extends CubeXProgramPiece
{

	private String name;
	private ArrayList<CubeXTypeVariable> types;
	private ArrayList<CubeXArgument> arglist;
	private CubeXType returnType;
	
	private CubeXStatement statement;

	public CubeXFunction(CubeXFunctionDeclaration decl, CubeXStatement stat)
	{
		name=decl.name;
		types=decl.scheme.types;
		arglist=decl.scheme.arglist;
		returnType=decl.scheme.returnType;
		
		statement=stat;
	}
	
	public CubeXFunction(CubeXFunctionDeclaration decl, CubeXExpression expr)
	{
		this(decl, new CubeXReturnStatement(expr));
	}
	
	public CubeXFunction(CubeXFunctionDeclaration decl)
	{
		this(decl, (CubeXStatement)null);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("fun ").append(name).append(" < ");
		
		String prefix="";
		for(CubeXType t : types)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		
		prefix="";
		for(CubeXArgument a : arglist)
		{
			sb.append(prefix).append(a.toString()).append(" ");
			prefix=", ";
		}
		sb.append(") : ").append(returnType.toString()).append(" ").append(statement==null?";":statement.toString());
		return sb.toString();
	}
}
