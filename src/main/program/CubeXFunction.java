package main.program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import main.c.CUtils;
import main.c.GlobalAwareness;
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
import main.util.CubeXArgument;
import main.util.CubeXFunctionHeader;
import main.util.Tuple;


public class CubeXFunction extends CubeXProgramPiece
{
	private CubeXClassBase parentHolder;
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
		parentHolder=null;
		
		statement=stat;
	}
	
	public CubeXFunction(CubeXFunctionHeader decl, CubeXExpression expr)
	{
		this(decl, new CubeXReturnStatement(expr));
	}

	public void setParent(CubeXClassBase p)
	{
		parentHolder=p;
	}
	public CubeXFunction(CubeXFunctionHeader decl)
	{
		this(decl, (CubeXStatement)null);
	}

	public boolean isFunction()
	{
		return true;
	}

	public CubeXClassBase getParent()
	{
		return parentHolder;
	}
	
	public ArrayList<CubeXArgument> getArglist() {
		return arglist;
	}

	public void setArglist(ArrayList<CubeXArgument> arglist) {
		this.arglist = arglist;
	}
	
	public CubeXType getReturnType() {
		return returnType;
	}

	public void setReturnType(CubeXType returnType) {
		this.returnType = returnType;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<CubeXTypeVariable> getTypes() {
		return types;
	}

	public boolean isDeclaration()
	{
		return statement==null;
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException {
		
		//Assumes funs are already added to funcontext
		
		TypeVariableContext funTypeVarCon = (TypeVariableContext) typeVarCon.createChildContext();
		for(CubeXTypeVariable tVar : types)
		{
			funTypeVarCon.add(tVar.getName(), tVar);
		}
		
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
		
		
		ArrayList<CubeXArgument> newArgs = new ArrayList<CubeXArgument>();
		for(CubeXArgument arg : arglist)
		{		
			CubeXType newType = CubeXType.validateType(arg.type, false,  classCon, funTypeVarCon);
			newArgs.add(new CubeXArgument(arg.variable, newType));
			newVarCon.add(arg.variable.getName(), newType);
		}
		this.arglist=newArgs;
		
		returnType=CubeXType.validateType(returnType, false, classCon, funTypeVarCon);
		
		Tuple<Boolean, CubeXType> res = statement.typecheck(force, classCon, funCon, newVarCon, funTypeVarCon, setField, par);
		if(!res.first)
			throw new TypeCheckException();
		if(!CubeXType.isSubType(res.second, returnType, classCon))
			throw new TypeCheckException("Incorrect function return type");
		
		return res;
	}
	
	@Override
	public String preC() {
		return "";
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		sb.append("object_t * ").append(CUtils.canonName(this, false)).append("(");

		String separator = "";
		if(this.parentHolder!=null)
		{
			sb.append("object_t *this");
			separator=", ";
		}
		for (CubeXArgument arg : arglist) {
			sb.append(separator).append(" object_t * ").append(" ").append(CUtils.canonName(arg.variable));
			separator = ", ";
		}
		sb.append(")");
			
		GlobalAwareness.declarationAppend(sb.toString() + ";");	
		sb.append("\n{\n");
		
		StringBuilder sbafter = new StringBuilder();
		String pre = statement.preC(this);
		if(!pre.isEmpty())
			sbafter.append(statement.preC(this)).append(";\n");
		sbafter.append(statement.toC(this)).append("\n}\n");
		
		HashSet<String> strArgs = new HashSet<String>();
		for(CubeXArgument ar : this.arglist)
		{
			strArgs.add(ar.variable.getName());
		}
		for(String var :locals)
		{
			if(strArgs.contains(var))
				continue;
			sb.append("\tobject_t * ").append(CUtils.canonName(var)).append(" = NULL;\n");
		}
		
		sb.append(sbafter.toString());
		
		return sb.toString();
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

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after) {
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		statement.initializeSucc(null);
		addSucc(after);
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals) 
	{
		statement.getUsedVariables(globals);
	}
	
	public HashSet<String> getInnerGlobals()
	{
		if(statement==null)
		{
			return new HashSet<String>();
		}
		return statement.getUsedVariables(true);
	}
}
