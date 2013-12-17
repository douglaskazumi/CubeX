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
import main.expression.CubeXFunctionCall;
import main.expression.CubeXVariable;
import main.program.CubeXClass;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.CubeXCompiler;
import main.util.Tuple;

public class CubeXIfStatement extends CubeXStatement {

	private CubeXExpression condition;
	private CubeXStatement ifstatement;
	private CubeXStatement elsestatement;
	
	public CubeXIfStatement(CubeXExpression condition, CubeXStatement ifstatement, CubeXStatement elsestatement)
	{
		this.condition = condition;
		this.ifstatement = ifstatement;
		if(elsestatement==null)
			elsestatement=new CubeXBlock();
		this.elsestatement = elsestatement;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) throws ContextException,TypeCheckException {
		
		CubeXType condType = condition.getType(force, classCon, funCon, varCon, typeVarCon, setField, par, parFunction);
		if(!condType.isBool())
			throw new TypeCheckException();
		
		boolean mutable = varCon.isMutable();
		VariableContext innerConTrue = (VariableContext)varCon.createChildContext();
		VariableContext innerConFalse = (VariableContext)varCon.createChildContext();
		
		Tuple<Boolean, CubeXType> resTrue = ifstatement.typecheck(force, classCon, funCon, innerConTrue, typeVarCon, false, par, parFunction);
		Tuple<Boolean, CubeXType> resFalse = elsestatement.typecheck(force, classCon, funCon, innerConFalse, typeVarCon, false, par, parFunction);
		
		varCon.setMutable(mutable);
		
		if(par!=null && par.isClass())
		{
			VariableContext testCon = new VariableContext(null);
			VariableContext.merge(testCon, innerConTrue, innerConFalse, classCon, typeVarCon);
			for(String varName : testCon.getInnerMap().keySet())
			{
				((CubeXClass)par).definedFields.add(varName);
			}
			
			
			innerConTrue.getInnerMap().clear();
			innerConFalse.getInnerMap().clear();
			
			resTrue = ifstatement.typecheck(true, classCon, funCon, innerConTrue, typeVarCon, setField, par, parFunction);
			resFalse = elsestatement.typecheck(true, classCon, funCon, innerConFalse, typeVarCon, setField, par, parFunction);
			
			varCon.setMutable(mutable);
		
		}
		
		VariableContext.merge(varCon, innerConTrue, innerConFalse, classCon, typeVarCon);
		
		if(!resTrue.first)
			return new Tuple<Boolean, CubeXType>(false, null);
		
		if(!resFalse.first && elsestatement.isBlock() && ((CubeXBlock)elsestatement).isEmpty())
			return new Tuple<Boolean, CubeXType>(true, resTrue.second);
		
		if(!resFalse.first)
			return new Tuple<Boolean, CubeXType>(true, resTrue.second);
		
		CubeXType returnType = CubeXType.join(resTrue.second, resFalse.second,classCon);
		
		return new Tuple<Boolean, CubeXType>(true, returnType) ;
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return condition.preC(par);
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		
		if(CubeXCompiler.optimizations)
			sb.append("if((bool)(").append(condition.toC(par)).append("))\n{\n");
		else
			sb.append("if((bool)isTrue(").append(condition.toC(par)).append("))\n{\n");
		
		sb.append(this.gcDeadVariables());
		sb.append("\t\t").append(ifstatement.preC(par));
		sb.append("\t\t").append(ifstatement.toC(par));
		sb.append("\t}\n"); 
		//When it does not have else, it's an empty block. If it is only one statment, is not a block
		if((elsestatement.isBlock() && !((CubeXBlock)elsestatement).isEmpty()) || !elsestatement.isBlock()){
			sb.append("\telse {\n");
			sb.append(this.gcDeadVariables());
			sb.append("\t\t").append(elsestatement.preC(par));
			sb.append("\t\t").append(elsestatement.toC(par));
			sb.append("\t}\n");
		}
		sb.append(condition.postC(par));
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("if ( ").append(condition.toString()).append(" ) ").append(ifstatement.toString()).append(" else ");
		if(elsestatement!=null)
		{
			sb.append(elsestatement.toString());
		}
		else
		{
			sb.append("{ }");
		}
		
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel) {
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns;
		addSucc(ifstatement, isTopLevel);
		addSucc(elsestatement, isTopLevel);
		returns = ifstatement.initializeSucc(after,  isTopLevel);
		returns.addAll(elsestatement.initializeSucc(after,  isTopLevel));
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		HashSet<CubeXVariable> usedVars = globals?usedVarsGlobals:usedVarsAll;
		usedVars.addAll(condition.getUsedVars(globals, ignoredFunctions));
		ifstatement.getUsedVariables(globals, ignoredFunctions);
		elsestatement.getUsedVariables(globals,ignoredFunctions);
	}

	@Override
	public void updateDeadVariables()
	{
		setDeadVariables();
		ifstatement.updateDeadVariables();
		elsestatement.updateDeadVariables();
	}
	
	@Override
	public CubeXProgramPiece flatten() {
		this.ifstatement = (CubeXStatement)ifstatement.flatten();
		this.elsestatement = (CubeXStatement)elsestatement.flatten();
				
		if(condition.isFunctionCall()){
			CubeXBlock flattened = new CubeXBlock();
			
			CubeXExpression originalCondition = CubeXFunctionCall.copy((CubeXFunctionCall)condition);
			
			CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), originalCondition);
			this.condition = tempVar.getVariable();
			flattened.add(tempVar);
			flattened.add(this);			
			return flattened;
		}
		
		return this;
	}

	@Override
	public void addBoxes()
	{	
		condition=condition.addBoxes();
		ifstatement.addBoxes();
		elsestatement.addBoxes();
	}
	
	@Override
	public void simplifyFunctionBoxes()
	{	
		condition=condition.simplifyFunctionBoxes();
		ifstatement.simplifyFunctionBoxes();
		elsestatement.simplifyFunctionBoxes();
	}
	
	@Override
	public void primitivifyVariables()
	{	
		condition=condition.primitivifyVariables();
		ifstatement.primitivifyVariables();
		elsestatement.primitivifyVariables();
	}
	
	@Override
	public void reduceBoxes()
	{	
		condition=Boxer.unboxify(condition).reduceBoxes();
		ifstatement.reduceBoxes();
		elsestatement.reduceBoxes();
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		ExpressionContext localCon = con.createChildContext();
		
		ExpressionContext ifCon = ifstatement.eliminateCommonSubexpressions(localCon);
		ExpressionContext elseCon = elsestatement.eliminateCommonSubexpressions(localCon);
		
		ifCon.merge(localCon, elseCon);
		
		return localCon;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXIfStatement))
			return false;
		
		CubeXIfStatement oI = (CubeXIfStatement) other;
		
		return condition.equals(oI.condition) && ifstatement.equals(oI.ifstatement) && elsestatement.equals(oI.elsestatement);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((ifstatement == null) ? 0 : ifstatement.hashCode());
		result = prime * result + ((elsestatement == null) ? 0 : elsestatement.hashCode());
		return result;
	}
}
