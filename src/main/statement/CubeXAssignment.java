package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.GlobalContexts;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.expression.CubeXVariable;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.util.Tuple;

public class CubeXAssignment extends CubeXStatement {

	private String name;
	private CubeXVariable variable;
	private CubeXExpression expr;
	
	public CubeXAssignment(String name,CubeXExpression expr)
	{
		this.name=name;
		this.expr=expr;
		this.variable=new CubeXVariable(name);
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException {
		
		CubeXType type = expr.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(type.isVariable() && (typeVarCon.lookup(((CubeXTypeVariable)type).getName())==null))
			throw new ContextException();
		
		variable.trySetField(setField, par);
		
		variable.isLocal=(!variable.isField() && GlobalContexts.variableContext.lookup(name)!=null);
		
		varCon.add(name, type);
		
		
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(expr.preC(par));
		
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) 
	{

		StringBuilder sb = new StringBuilder();
		String temp = CUtils.getTempName();
		sb.append("\t").append(CUtils.canonName(temp)).append(" = ").append(variable.toC(par)).append(";\n");
		sb.append("\t").append(variable.toC(par)).append(" = gc_inc(").append(expr.toC(par)).append(");\n");
		sb.append("\tgc(gc_dec(").append(CUtils.canonName(temp)).append("));\n");
		sb.append("\t").append(CUtils.canonName(temp)).append(" = NULL;\n");
		sb.append(expr.postC(par));
		if(!variable.isField() && par != null)
		{
			par.addLocal(name);
		}
		else if(!variable.isField())
		{
			GlobalAwareness.addLocal(name);
		}
		
		if(par!=null)
		{
			par.addLocal(temp);
		}
		else
		{
			GlobalAwareness.addLocal(temp);
		}
		sb.append(this.gcDeadVariables());
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" := ").append(expr.toString()).append(" ;");
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after)
	{
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		addSucc(after);
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals)
	{
		if(globals)
		{
			usedVarsGlobals.addAll(expr.getUsedVars(globals));
		}
		else
		{
			usedVarsAll.addAll(expr.getUsedVars(globals));
		}
		
	}

	@Override
	public void initializeDefinedVariables()
	{
		definedVars.add(variable.getName());		
	}
	
}
