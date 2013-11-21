package main.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.Optimizations.Boxer;
import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.expression.CubeXFunctionCall;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.CubeXArgument;
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
		
		boolean primReturnValue =(par!=null && par.isFunction() && (((CubeXFunction)par).getReturnType().isBool()||((CubeXFunction)par).getReturnType().isInt()));
		
		StringBuilder sb = new StringBuilder();
		
		if(primReturnValue)
			sb.append("\t").append(CUtils.canonName(temp)).append(" = (object_t *)(").append(returnValue.toC(par)).append(");\n");
		else
			sb.append("\t").append(CUtils.canonName(temp)).append(" = gc_inc(").append(returnValue.toC(par)).append(");\n");
		
		sb.append(returnValue.postC(par));
		//sb.append(this.gcDeadVariables());
		
		HashMap<String, Boolean> locals;
		if(par==null)
		{
			locals=GlobalAwareness.locals;
		}
		else
		{
			locals=par.locals;
		}
		locals.put(temp, returnValue.getTypeUnsafe().isInt() || returnValue.getTypeUnsafe().isBool());
		ArrayList<String> toIgnore = new ArrayList<String>();
		if(par!=null && par.isFunction())
		{
			for(CubeXArgument var : ((CubeXFunction)par).getArglist())
			{	
				toIgnore.add(var.variable.getName());
				if(var.type.isBool()||var.type.isInt())
					continue;
				sb.append("\tgc(gc_dec(").append(CUtils.canonName(var.variable.getName())).append("));\n");
			}
		}
		for(String var : locals.keySet())
		{
			if(var.equals(temp) || toIgnore.contains(var))
				continue;
			if(locals.get(var))
				continue;
			sb.append("\tgc(gc_dec(").append(CUtils.canonName(var)).append("));\n");
		}
		if(par!=null && par.isFunction() && ((CubeXFunction)par).getParent()!=null)
		{
			sb.append("gc_dec(this);\n");
		}
		
		
		if(primReturnValue)
			sb.append("\treturn (" + CUtils.canonName(temp) + ");\n");
		else
			sb.append("\treturn gc_dec(" + CUtils.canonName(temp) + ");\n");
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("return ").append(returnValue.toString()).append(" ;");
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel)
	{
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		returns.add(this);
		
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		HashSet<String> usedVars = globals?usedVarsGlobals:usedVarsAll;
		usedVars.addAll(returnValue.getUsedVars(globals, ignoredFunctions));
	}

	@Override
	public void updateDeadVariables()
	{
		setDeadVariables();
	}

	@Override
	public CubeXProgramPiece flatten() {
		if(returnValue.isFunctionCall()){
			CubeXBlock flattened = new CubeXBlock();
			CubeXFunctionCall originalReturn = CubeXFunctionCall.copy((CubeXFunctionCall)returnValue);
			CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), originalReturn);
			this.returnValue = tempVar.getVariable();
			flattened.add(tempVar);
			flattened.add(this);			
			return flattened;
		}
		
		return this;
	}

	@Override
	public void addBoxes()
	{
		returnValue=returnValue.addBoxes();
	}
	
	@Override
	public void simplifyFunctionBoxes()
	{
		returnValue=returnValue.simplifyFunctionBoxes();
	}

	@Override
	public void primitivifyVariables() {		
		returnValue=returnValue.primitivifyVariables();
		if( returnValue.getTypeUnsafe().isBool() || returnValue.getTypeUnsafe().isInt())
			returnValue=Boxer.unboxify(returnValue);
		
	}
	
	@Override
	public void reduceBoxes() {
		returnValue=returnValue.reduceBoxes();
		
	}
}
