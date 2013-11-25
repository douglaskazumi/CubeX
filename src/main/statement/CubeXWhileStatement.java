package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.Boxer;
import main.Optimizations.ExpressionContext;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXWhileStatement extends CubeXStatement 
{
	private CubeXExpression condition;
	private CubeXStatement whilestatement;
	
	public CubeXWhileStatement(CubeXExpression condition, CubeXStatement whilestatement)
	{
		this.condition = condition;
		this.whilestatement = whilestatement;
	}
	
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		CubeXType condType = condition.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(!condType.isBool())
			throw new TypeCheckException("While condition not a bool");
		boolean mutable = varCon.isMutable();
		whilestatement.typecheck(force, classCon, funCon, (VariableContext) varCon.createChildContext(), typeVarCon, false, par);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}


	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}


	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("while(true)\n\t{\n");
		sb.append(condition.preC(par));
		sb.append("\tif(!((bool)").append(condition.toC(par)).append("))\n\t");
		sb.append("{\n");
		sb.append(condition.postC(par));
		sb.append("\t\tbreak;\n\t}\n");
		sb.append(condition.postC(par)).append("\n");	
		sb.append("\t\t").append(whilestatement.preC(par));
		sb.append("\t\t").append(whilestatement.toC(par));
		sb.append("\t}\n");
		sb.append(this.gcDeadVariables());
		return sb.toString();
	}


	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("while ( ").append(condition.toString()).append(" ) ").append(whilestatement.toString());
		return sb.toString();
	}


	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel)
	{
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns;
		addSucc(this.whilestatement, isTopLevel);
		returns = whilestatement.initializeSucc(this, isTopLevel);
		addSucc(after, isTopLevel);
		return returns;
	}


	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		HashSet<String> usedVars = globals?usedVarsGlobals:usedVarsAll;
		usedVars.addAll(condition.getUsedVars(globals, ignoredFunctions));
		whilestatement.getUsedVariables(globals, ignoredFunctions);
	}


	@Override
	public void updateDeadVariables() {
		setDeadVariables();
		whilestatement.updateDeadVariables();
	}
	
	@Override
	public CubeXProgramPiece flatten() {
		whilestatement = (CubeXStatement)whilestatement.flatten();
		
		return this;
	}


	@Override
	public void addBoxes()
	{
		condition=condition.addBoxes();
		whilestatement.addBoxes();
	}


	@Override
	public void simplifyFunctionBoxes() {
		condition=condition.simplifyFunctionBoxes();
		whilestatement.simplifyFunctionBoxes();		
	}
	
	@Override
	public void primitivifyVariables() {
		condition=condition.primitivifyVariables();
		whilestatement.primitivifyVariables();		
	}
	
	@Override
	public void reduceBoxes() {
		condition=Boxer.unboxify(condition).reduceBoxes();
		whilestatement.reduceBoxes();		
	}


	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		ExpressionContext localCon = con.createChildContext();
		
		ExpressionContext whileCon = whilestatement.eliminateCommonSubexpressions(localCon);
		ExpressionContext addToLocalCon = new ExpressionContext(null);
		whileCon.merge(addToLocalCon, localCon);
		
		localCon.addAll(addToLocalCon);
		
		return localCon;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXWhileStatement))
			return false;
		
		CubeXWhileStatement oW = (CubeXWhileStatement) other;
		
		return condition.equals(oW.condition) && whilestatement.equals(oW.whilestatement);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((whilestatement == null) ? 0 : whilestatement.hashCode());
		return result;
	}
}
