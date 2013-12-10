package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.ExpressionContext;
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
import main.program.CubeXClassYielder;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.type.CubeXTypeYielder;
import main.util.Tuple;

public class CubeXForStatement extends CubeXStatement {

	private String variable;
	private CubeXExpression forexpression;
	private CubeXStatement forbody;
	private String indexer;
	private String iterable;
	private CubeXVariable actualVariable;
	
	public CubeXForStatement(String variable, CubeXExpression forexpression, CubeXStatement forbody)
	{
		this.variable = variable;
		this.forbody = forbody;
		this.forexpression = forexpression;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException,TypeCheckException 
	{
		CubeXType forExprType = forexpression.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(!forExprType.isIterable() && !forExprType.isYielder())
			throw new TypeCheckException("Not iterable or yielder");
		
		CubeXType innerType;
		if(forExprType.isYielder())
		{
			CubeXTypeYielder forIterable = (CubeXTypeYielder)forExprType;
			innerType=forIterable.getInnerType();	
		}
		else if (forExprType.isIterable())
		{
			CubeXTypeIterable forIterable = (CubeXTypeIterable)forExprType;
			innerType=forIterable.getInnerType();	

		}
		else
			throw new TypeCheckException("Not iterable or yielder");
		boolean mutable = varCon.isMutable();
		VariableContext innerCon = (VariableContext)varCon.createChildContext();
		innerCon.add(variable, innerType);
		actualVariable=new CubeXVariable(variable);
		actualVariable.getType(force, classCon, funCon, innerCon, typeVarCon, setField, par);

		Tuple<Boolean, CubeXType> ret = forbody.typecheck(force, classCon, funCon, innerCon, typeVarCon, false, par, isYielder);
		varCon.setMutable(mutable);
		
		return ret;
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(forexpression.preC(par));
		CubeXType forExprType = forexpression.getTypeUnsafe();
		if(!forExprType.isYielder())
			indexer = CUtils.getTempName();
		iterable = CUtils.getTempName();
		return sb.toString();
	}
	
	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		
		CubeXType forExprType = forexpression.getTypeUnsafe();
		
		if(par!=null)
		{
			if(!forExprType.isYielder())
				par.addLocal(indexer, false);
			par.addLocal(iterable, false);
			par.addLocal(variable, false);
		}
		else
		{
			if(!forExprType.isYielder())
				GlobalAwareness.addLocal(indexer, false);
			GlobalAwareness.addLocal(iterable, false);
			GlobalAwareness.addLocal(variable, false);
		}
		
		CubeXType innerType;
		if(forExprType.isYielder())
		{
			CubeXTypeYielder forIterable = (CubeXTypeYielder)forExprType;
			CubeXClassYielder yielderClss = null;
			try
			{
				 yielderClss = (CubeXClassYielder) forIterable.getDeclaration(GlobalContexts.classContext);
			} catch (ContextException e)
			{
				e.printStackTrace();
			}
			
			String yielderClassName = yielderClss.getName();
			
			innerType=forIterable.getInnerType();	
			sb.append(CUtils.canonName(iterable)).append(" = ").append("(object_t *)").append("createYielder(").append(yielderClss.myTypeID).append(");\n");
			sb.append("while(true){").append(System.lineSeparator());
			sb.append(CUtils.canonName(iterable)).append(" = (object_t *)y_").append(yielderClassName).append("((yielder_").append(yielderClassName).append("_t *)").append(CUtils.canonName(iterable)).append(", gc_inc(").append(forexpression.toC(par)).append("));").append(System.lineSeparator());
			sb.append("if(((yielder_t *)").append(CUtils.canonName(iterable)).append(")->status==-1)").append(System.lineSeparator());
			sb.append("break;").append(System.lineSeparator());
			
			sb.append(CUtils.canonName(variable)).append(" = ((yielder_t *)").append(CUtils.canonName(iterable)).append(")->returnValue;").append(System.lineSeparator());
			
			String pre = forbody.preC(par);
			if(!pre.isEmpty())
				sb.append("\t\t").append(pre);
			
			sb.append("\t\t").append(forbody.toC(par));
			sb.append("\t\tgc(gc_dec(").append(CUtils.canonName(variable)).append("));\n");
			sb.append("\t\t").append(CUtils.canonName(variable)).append(" = NULL;\n");
			sb.append("\t}\n");
			sb.append("\t\tgc(gc_dec(").append(CUtils.canonName(iterable)).append("));\n");
			sb.append("\t\t").append(CUtils.canonName(iterable)).append(" = NULL;\n");
			sb.append(forexpression.postC(par));
			sb.append(this.gcDeadVariables());
			
			return sb.toString();
		}
		else 
		{
			CubeXTypeIterable forIterable = (CubeXTypeIterable)forExprType;
			innerType=forIterable.getInnerType();	

		}		
		
		
		//NOT YIELDER HERE
		
		boolean isInnerPrim = innerType.isBool() || innerType.isInt();
		isInnerPrim = false;
		
		sb.append(CUtils.canonName(indexer)).append(" = (object_t *)createIndexer();\n");
		sb.append("\t").append(CUtils.canonName(iterable)).append(" = gc_inc(").append(forexpression.toC(par)).append(");\n");
		sb.append("\twhile(iterableHasNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("))\n\t{\n");

		sb.append("\t\t").append(CUtils.canonName(variable)).append(" = gc_inc(iterableNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("));\n");
		
		String pre = forbody.preC(par);
		if(!pre.isEmpty())
			sb.append("\t\t").append(pre);
		sb.append("\t\t").append(forbody.toC(par));
		if(!isInnerPrim)
			sb.append("\t\tgc(gc_dec(").append(CUtils.canonName(variable)).append("));\n");
		sb.append("\t\t").append(CUtils.canonName(variable)).append(" = NULL;\n");
		sb.append("\t}\n");
		sb.append("\t\tgc_iterableIndex((iterableIndex_t *)").append(CUtils.canonName(indexer)).append(");\n");
		sb.append("\t\t").append(CUtils.canonName(indexer)).append(" = NULL;\n");
		sb.append("\t\tgc(gc_dec(").append(CUtils.canonName(iterable)).append("));\n");
		sb.append("\t\t").append(CUtils.canonName(iterable)).append(" = NULL;\n");
		sb.append(forexpression.postC(par));
		sb.append(this.gcDeadVariables());
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("for ( ").append(variable).append(" in ").append(forexpression.toString()).append(" ) ").append(forbody.toString());
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel) {
		GlobalAwareness.allNode.add(this);
		ArrayList<CubeXProgramPiece> returns;
		addSucc(forbody, isTopLevel);
		returns = forbody.initializeSucc(this, isTopLevel);
		addSucc(after, isTopLevel);
		return returns;
	}

	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions) 
	{
		HashSet<CubeXVariable> usedVars = globals?usedVarsGlobals:usedVarsAll;
		usedVars.addAll(forexpression.getUsedVars(globals, ignoredFunctions));
		forbody.getUsedVariables(globals, ignoredFunctions);
	}

	@Override
	public void initializeDefinedVariables()
	{
		definedVars.add(actualVariable);		
	}

	@Override
	public void updateDeadVariables() {
		setDeadVariables();
		forbody.updateDeadVariables();	
	}
	
	@Override
	public CubeXProgramPiece flatten() {
		this.forbody = (CubeXStatement)forbody.flatten();
		
		return this;
	}

	@Override
	public void addBoxes()
	{
		forexpression=forexpression.addBoxes();
		forbody.addBoxes();
	}

	@Override
	public void simplifyFunctionBoxes() {
		forexpression=forexpression.simplifyFunctionBoxes();
		forbody.simplifyFunctionBoxes();
	}
	
	@Override
	public void primitivifyVariables() {
		forexpression=forexpression.primitivifyVariables();
		forbody.primitivifyVariables();
	}
	
	@Override
	public void reduceBoxes() {
		forexpression=forexpression.reduceBoxes();
		forbody.reduceBoxes();
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		ExpressionContext localCon = con.createChildContext();
		
		ExpressionContext forCon = forbody.eliminateCommonSubexpressions(localCon);
		ExpressionContext addToLocalCon = new ExpressionContext(null);
		forCon.merge(addToLocalCon, localCon);
		
		localCon.addAll(addToLocalCon);
		
		return localCon;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXForStatement))
			return false;
		
		CubeXForStatement oF = (CubeXForStatement) other;
		
		return forexpression.equals(oF.forexpression) && forbody.equals(oF.forbody);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((forexpression == null) ? 0 : forexpression.hashCode());
		result = prime * result + ((forbody == null) ? 0 : forbody.hashCode());
		return result;
	}
}
