package main.statement;
import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.ExpressionContext;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXVariable;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.Tuple;


public class CubeXBlock extends CubeXStatement
{

	private ArrayList<CubeXStatement> innerStatements;
	
	public CubeXBlock()
	{
		innerStatements = new ArrayList<CubeXStatement>();
	}
	
	public boolean isBlock()
	{
		return true;
	}

	public boolean isEmpty()
	{
		return innerStatements.isEmpty();
	}

	public void add(CubeXStatement stat)
	{
		if(stat.isBlock())
			innerStatements.addAll(((CubeXBlock)stat).innerStatements);
		else
			innerStatements.add(stat);
	}
	
	public CubeXStatement reduceBlock()
	{
		if(innerStatements.size()==0 || innerStatements.size()>1)
			return this;
		else
			return innerStatements.get(0);
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) throws ContextException,TypeCheckException 
	{
		boolean willReturn=false;
		CubeXType returnType=CubeXType.getNothing();
		for(CubeXStatement stat : innerStatements)
		{
			Tuple<Boolean, CubeXType> res = stat.typecheck(force, classCon, funCon, varCon, typeVarCon, setField, par, parFunction);
			if(res.first)
			{
				willReturn=true;
				returnType=CubeXType.join(res.second, returnType,classCon);
			}
		}
		
		if(willReturn==false)
			returnType=null;
		return new Tuple<Boolean, CubeXType>(willReturn, returnType);
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		for(CubeXStatement stat: innerStatements){
			sb.append("\t\t").append(stat.preC(par));
			sb.append("\t\t").append(stat.toC(par));
		}
		sb.append(this.gcDeadVariables());
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(CubeXStatement stat : innerStatements)
		{
			sb.append(" ").append(stat.toString());
		}
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel) 
	{
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		if(innerStatements.size()==0)
		{
			addSucc(after, isTopLevel);
			return returns;
		}
		
		addSucc(innerStatements.get(0), isTopLevel);
		
		CubeXStatement cur = innerStatements.get(0);
		for(int i=0; i<innerStatements.size()-1; ++i)
		{
			CubeXStatement next = innerStatements.get(i+1);
			returns.addAll(cur.initializeSucc(next, isTopLevel));
			cur=next;
		}
		returns.addAll(cur.initializeSucc(after, isTopLevel));
		return returns;
		
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions) 
	{
		HashSet<CubeXVariable> usedVars = globals?usedVarsGlobals:usedVarsAll;
		for(CubeXStatement stat : innerStatements)
		{
			usedVars.addAll(stat.getUsedVariables(globals, ignoredFunctions));
		}
	}

	@Override
	public void updateDeadVariables() {
		setDeadVariables();
		for(CubeXStatement stat : innerStatements)
		{
			stat.updateDeadVariables();
		}
	}

	@Override
	public CubeXProgramPiece flatten() {
		ArrayList<CubeXStatement> flattenedStatements = new ArrayList<CubeXStatement>();
		for(CubeXStatement stat : innerStatements){
			CubeXStatement flattened = (CubeXStatement)stat.flatten();
			if(flattened.isStatement() && flattened.isBlock()){
				flattenedStatements.addAll(((CubeXBlock)flattened).getInnerStatements());
			}
			else{
				flattenedStatements.add(flattened);
			}
		}
		innerStatements = flattenedStatements;
		
		
		return this;
	}

	public ArrayList<CubeXStatement> getInnerStatements() {
		return innerStatements;
	}

	@Override
	public void addBoxes() 
	{
		for(CubeXStatement stat : innerStatements)
		{
			stat.addBoxes();
		}
		
	}
	
	@Override
	public void simplifyFunctionBoxes() 
	{
		for(CubeXStatement stat : innerStatements)
		{
			stat.simplifyFunctionBoxes();
		}
		
	}
	
	@Override
	public void primitivifyVariables() 
	{
		for(CubeXStatement stat : innerStatements)
		{
			stat.primitivifyVariables();
		}
		
	}
	
	@Override
	public void reduceBoxes() 
	{
		for(CubeXStatement stat : innerStatements)
		{
			stat.reduceBoxes();
		}
		
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		
		for(CubeXStatement stat : innerStatements){
			stat.eliminateCommonSubexpressions(con);
		}
		return con;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXBlock))
			return false;
		
		CubeXBlock oB = (CubeXBlock) other;
		
		if(innerStatements.size() != oB.innerStatements.size())
			return false;
		
		for(int i = 0; i < innerStatements.size(); i++){
			if(!(innerStatements.get(i).equals(oB.innerStatements.get(i))))
				return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		for(int i = 0; i < innerStatements.size(); i++){
			result = prime * result + innerStatements.get(i).hashCode();
		}
		
		return result;
	}
}
