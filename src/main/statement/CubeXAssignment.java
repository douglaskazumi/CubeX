package main.statement;

import java.util.ArrayList;
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
import main.type.CubeXTypeVariable;
import main.util.Tuple;

public class CubeXAssignment extends CubeXStatement {

	private String name;
	private CubeXVariable variable;
	private CubeXExpression expr;
	boolean isPrimitive = false;
	private CubeXType previousType;
	
	public void setExpr(CubeXExpression expr) {
		this.expr = expr;
	}

	public CubeXAssignment(String name,CubeXExpression expr)
	{
		this.name=name;
		this.expr=expr;
		this.variable=new CubeXVariable(name);
	}
	
	public CubeXVariable getVariable(){
		return variable;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException, TypeCheckException {
		
		CubeXType type = expr.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(type.isVariable() && (typeVarCon.lookup(((CubeXTypeVariable)type).getName())==null))
			throw new ContextException();
		

		
		previousType=varCon.lookup(name);
		varCon.add(name, type);
		
		variable.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		
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
		/*if(gcAfter.contains(variable.getName()))
			return "";*/

		StringBuilder sb = new StringBuilder();
		String temp = CUtils.getTempName();
		boolean wasPrimitive = previousType!=null && (previousType.isBool()||previousType.isInt());
		wasPrimitive=false;
		if((!wasPrimitive && previousType!=null) || (previousType==null && !isPrimitive) )
			sb.append("\t").append(CUtils.canonName(temp)).append(" = (object_t *)(").append(variable.toC(par)).append(");\n");
		if(isPrimitive && !variable.isField())
		{
			
			boolean isArg = (par!=null&&par.isFunction())?((CubeXFunction)par).isArg(variable):false;

			String cast = isArg?"(int)":"(object_t *)";
			sb.append("\t").append(variable.toC(par)).append(" = ").append(cast).append(expr.toC(par)).append(";\n");
		}
		else
		{
			sb.append("\t").append(variable.toC(par)).append(" = (gc_inc((").append(expr.toC(par)).append(")));\n");
		}
		
		
		if((!wasPrimitive && previousType!=null) || (previousType==null && !isPrimitive) )
		{
			sb.append("\tgc(gc_dec(").append(CUtils.canonName(temp)).append("));\n");	
			sb.append("\t").append(CUtils.canonName(temp)).append(" = NULL;\n");
		}

		sb.append(expr.postC(par));
		
		if(!variable.isField() && par != null)
		{
			par.addLocal(name, isPrimitive);
		}
		else if(!variable.isField())
		{
			GlobalAwareness.addLocal(name, isPrimitive);
		}
		
		if((!wasPrimitive && previousType!=null) || (previousType==null && !isPrimitive) )
		{
			if(par!=null)
			{
				par.addLocal(temp, wasPrimitive);
			}
			else
			{
				GlobalAwareness.addLocal(temp, wasPrimitive);
			}
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
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel)
	{
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		addSucc(after, isTopLevel);
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		if(globals)
		{
			usedVarsGlobals.addAll(expr.getUsedVars(globals, ignoredFunctions));
		}
		else
		{
			usedVarsAll.addAll(expr.getUsedVars(globals, ignoredFunctions));
		}
		
	}

	@Override
	public void initializeDefinedVariables()
	{
		if(!variable.isField())
			definedVars.add(variable);		
	}

	@Override
	public void updateDeadVariables()
	{
		setDeadVariables();
	}
	
	@Override
	public CubeXProgramPiece flatten() {
		CubeXBlock flattened = new CubeXBlock();
		boolean wasFlattened = false;
		
		if(expr.isFunctionCall() && ((CubeXFunctionCall)expr).isNested()){
			flattened.add(((CubeXFunctionCall)expr).flatten());
			wasFlattened = true;
		}
		
		if(expr.isAppend()){
			CubeXStatement ret = ((CubeXAppend)expr).flatten();
			if(ret.isBlock() && ((CubeXBlock)ret).isEmpty()){
				wasFlattened = false;
			}
			else{
				flattened.add(ret);
				wasFlattened = true;
			}
		}
		
		if(expr.isIterable()){
			CubeXStatement ret = ((CubeXIterable)expr).flatten();
			if(ret.isBlock() && ((CubeXBlock)ret).isEmpty()){
				wasFlattened = false;
			}
			else{
				flattened.add(ret);
				wasFlattened = true;
			}
		}
		
		if(wasFlattened){
			flattened.add(this);
			return flattened;
		}
		
		return this;
	}

	@Override
	public void addBoxes()
	{
		expr=expr.addBoxes();			
	}

	@Override
	public void simplifyFunctionBoxes() {
		expr=expr.simplifyFunctionBoxes();
	}	
	
	@Override
	public void primitivifyVariables() {
		
		if((expr.getTypeUnsafe().isInt() || expr.getTypeUnsafe().isBool()) && !variable.isField())
		{
			isPrimitive=true;
			expr=Boxer.unboxify(expr);
		}
		expr.primitivifyVariables();
	}	
	
	@Override
	public void reduceBoxes() {
		expr=expr.reduceBoxes();
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		ExpressionContext localCon = con.createChildContext();
		
		CubeXExpression previousExpr = localCon.lookup(variable);
		if(previousExpr != null){
			if(!(previousExpr.equals(expr))){
				//if context contains variable, invalidates (replace variable in expressions on the context)
				localCon.invalidateExpressionsUsing(variable);
			}
		}

		for(CubeXVariable var : localCon.getAllVariables()){
			CubeXExpression replaceVariable =  localCon.lookup(var);
			if(expr.contains(var) && replaceVariable.isVariable()){
				expr.replace(var, replaceVariable);
			}
		}
		localCon.add(variable, expr);
		
		//replace expr with variables already in context
		CubeXVariable previousVariable = localCon.getVariableHolding(expr,variable);
		if(previousVariable != null && previousVariable != variable){
			expr = previousVariable;
		}
		
		return localCon;
	}	
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXAssignment))
			return false;
		
		CubeXAssignment oA = (CubeXAssignment) other;
		
		return variable.equals(oA.variable) && expr.equals(oA.expr);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((variable == null) ? 0 : variable.hashCode());
		result = prime * result + ((expr == null) ? 0 : expr.hashCode());
		return result;
	}
}
