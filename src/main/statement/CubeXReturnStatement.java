package main.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.Optimizations.Boxer;
import main.Optimizations.ExpressionContext;
import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXAppend;
import main.expression.CubeXExpression;
import main.expression.CubeXFunctionCall;
import main.expression.CubeXIterable;
import main.expression.CubeXVariable;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.CubeXArgument;
import main.util.CubeXCompiler;
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
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon,	VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par)	throws ContextException, TypeCheckException {
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
		primReturnValue &=CubeXCompiler.optimizations;
		
		
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
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		returns.add(this);
		this.setTopLevel(isTopLevel);
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		HashSet<CubeXVariable> usedVars = globals?usedVarsGlobals:usedVarsAll;
		usedVars.addAll(returnValue.getUsedVars(globals, ignoredFunctions));
	}

	@Override
	public void updateDeadVariables()
	{
		setDeadVariables();
	}

	@Override
	public CubeXProgramPiece flatten() {
		if(returnValue.isFunctionCall() || returnValue.isAppend() || returnValue.isIterable()){
			CubeXBlock flattened = new CubeXBlock();
			
			CubeXExpression originalReturn = null;
			if(returnValue.isFunctionCall())
				originalReturn = CubeXFunctionCall.copy((CubeXFunctionCall)returnValue);
			else if(returnValue.isAppend())
				originalReturn = CubeXAppend.copy((CubeXAppend)returnValue);
			else if(returnValue.isIterable())
				originalReturn = CubeXIterable.copy((CubeXIterable)returnValue);
			
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

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) {
		return con;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXReturnStatement))
			return false;
		
		CubeXReturnStatement oR = (CubeXReturnStatement) other;
		
		return returnValue.equals(oR.returnValue);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((returnValue == null) ? 0 : returnValue.hashCode());
		return result;
	}
}
