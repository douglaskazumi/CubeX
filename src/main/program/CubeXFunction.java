package main.program;
import java.util.ArrayList;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.statement.CubeXReturnStatement;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.type.Tuple;


public class CubeXFunction extends CubeXProgramPiece
{

	private String name;
	private ArrayList<CubeXTypeVariable> types;

	private ArrayList<CubeXArgument> arglist;
	private CubeXType returnType;
	
	private CubeXStatement statement;
	
	

	public CubeXFunction(CubeXFunctionHeader decl, CubeXStatement stat)
	{
		name=decl.name;
		types=decl.scheme.types;
		arglist=decl.scheme.arglist;
		returnType=decl.scheme.returnType;
		
		statement=stat;
	}
	
	public String getName() {
		return name;
	}

	public CubeXFunction(CubeXFunctionHeader decl, CubeXExpression expr)
	{
		this(decl, new CubeXReturnStatement(expr));
	}
	
	public CubeXFunction(CubeXFunctionHeader decl)
	{
		this(decl, (CubeXStatement)null);
	}
	
	
	public ArrayList<CubeXTypeVariable> getTypes() {
		return types;
	}

	public ArrayList<CubeXArgument> getArglist() {
		return arglist;
	}

	public CubeXType getReturnType() {
		return returnType;
	}
	
	public boolean isDeclaration()
	{
		return statement==null;
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

	/*
	private String name;
	private ArrayList<CubeXTypeVariable> types;

	private ArrayList<CubeXArgument> arglist;
	private CubeXType returnType;
	
	private CubeXStatement statement;
	*/
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException {
		
		//Assumes funs are already added to funcontext
		
		TypeVariableContext funTypeVarCon = (TypeVariableContext) typeVarCon.createChildContext();
		for(CubeXTypeVariable tVar : types)
		{
			funTypeVarCon.add(tVar.getName(), tVar);
		}
		
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
		
		for(CubeXArgument arg : arglist)
		{
			CubeXType.validateType(arg.type, classCon, funTypeVarCon);
			newVarCon.add(arg.variable.getName(), arg.type);
		}
		
		CubeXType.validateType(returnType, classCon, funTypeVarCon);
		
		Tuple<Boolean, CubeXType> res = statement.typecheck(classCon, funCon, newVarCon, funTypeVarCon);
		if(!res.first)
			throw new TypeCheckException();
		
		return res;
	}
}
