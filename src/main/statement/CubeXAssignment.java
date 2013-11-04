package main.statement;

import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
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
		/*
		if(par!=null && par.isClass())
			((CubeXClass) par).definedFields.add(name);*/
		
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
		sb.append("\t").append(CUtils.canonName(temp)).append(" = gc_inc_f(gc_dec(").append(variable.toC(par)).append("));\n");
		sb.append("\t").append(variable.toC(par)).append(" = gc_inc(").append(expr.toC(par)).append(");\n");
		sb.append("\tgc(gc_dec_f(").append(CUtils.canonName(temp)).append("));\n");
		sb.append("\t").append(CUtils.canonName(temp)).append(" = NULL;\n");
		if(!variable.isField() && par != null)
		{
			par.addLocal(name);
			par.addLocal(temp);
		}
		else if(!variable.isField())
		{
			GlobalAwareness.addLocal(name);
			GlobalAwareness.addLocal(temp);
		}
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" := ").append(expr.toString()).append(" ;");
		return sb.toString();
	}

	
}
