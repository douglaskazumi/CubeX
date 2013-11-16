package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

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
import main.program.CubeXArgument;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXReturnStatement extends CubeXStatement {

	private CubeXExpression returnValue;
	
	public CubeXReturnStatement(CubeXExpression returnValue)
	{
		this.returnValue = returnValue;
	}
	
	public boolean isReturn()
	{
		return true;
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon,	VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par)	throws ContextException, TypeCheckException {
		CubeXType type = returnValue.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		return new Tuple<Boolean, CubeXType>(true, type);
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return returnValue.preC(par);
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		String temp = CUtils.getTempName();
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append(CUtils.canonName(temp)).append(" = gc_inc(").append(returnValue.toC(par)).append(");\n");
		sb.append(returnValue.postC(par));
		
		HashSet<String> locals;
		if(par==null)
		{
			locals=GlobalAwareness.locals;
		}
		else
		{
			locals=par.locals;
		}
		locals.add(temp);
		ArrayList<String> toIgnore = new ArrayList<String>();
		if(par!=null && par.isFunction())
		{
			for(CubeXArgument var : ((CubeXFunction)par).getArglist())
			{
				toIgnore.add(var.variable.getName());
				sb.append("\tgc(gc_dec(").append(CUtils.canonName(var.variable.getName())).append("));\n");
			}
		}
		for(String var : locals)
		{
			if(var.equals(temp) || toIgnore.contains(var))
				continue;
			sb.append("\tgc(gc_dec(").append(CUtils.canonName(var)).append("));\n");
		}
		if(par!=null && par.isFunction() && ((CubeXFunction)par).getParent()!=null)
		{
			sb.append("gc_dec(this);\n");
		}
		sb.append("\treturn gc_dec(" + CUtils.canonName(temp) + ");\n");
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("return ").append(returnValue.toString()).append(" ;");
		return sb.toString();
	}

}
