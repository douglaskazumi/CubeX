package main.statement;

import java.util.ArrayList;
import java.util.HashSet;

import main.Optimizations.ExpressionContext;
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
import main.expression.CubeXVariable;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.util.CubeXCompiler;
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
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) throws ContextException,TypeCheckException 
	{
		CubeXType forExprType = forexpression.getType(force, classCon, funCon, varCon, typeVarCon, setField, par, parFunction);
		if(!forExprType.isIterable())
			throw new TypeCheckException();
		CubeXTypeIterable forIterable = (CubeXTypeIterable)forExprType;
		CubeXType innerType=forIterable.getInnerType();		
		boolean mutable = varCon.isMutable();
		VariableContext innerCon = (VariableContext)varCon.createChildContext();
		innerCon.add(variable, innerType);
		actualVariable=new CubeXVariable(variable);
		actualVariable.getType(force, classCon, funCon, innerCon, typeVarCon, setField, par, parFunction);

		forbody.typecheck(force, classCon, funCon, innerCon, typeVarCon, false, par, parFunction);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(forexpression.preC(par));
		indexer = CUtils.getTempName();
		iterable = CUtils.getTempName();
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		
		CubeXType innerType = ((CubeXTypeIterable)forexpression.getTypeUnsafe()).getInnerType();
		boolean isInnerPrim = innerType.isBool() || innerType.isInt();
		isInnerPrim &=CubeXCompiler.optimizations;
		
		if(par!=null)
		{
			par.addLocal(indexer, false);
			par.addLocal(iterable, false);
			par.addLocal(variable, isInnerPrim);
		}
		else
		{
			GlobalAwareness.addLocal(indexer, false);
			GlobalAwareness.addLocal(iterable, false);
			GlobalAwareness.addLocal(variable, isInnerPrim);
		}
		sb.append(CUtils.canonName(indexer)).append(" = (object_t *)createIndexer();\n");
		sb.append("\t").append(CUtils.canonName(iterable)).append(" = gc_inc(").append(forexpression.toC(par)).append(");\n");
		sb.append("\twhile(iterableHasNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("))\n\t{\n");
		if(isInnerPrim)
		{
			sb.append("\t\t").append(CUtils.canonName(variable)).append(" = (object_t *)unbox(iterableNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("));\n");
		}
		else
		{
			sb.append("\t\t").append(CUtils.canonName(variable)).append(" = gc_inc(iterableNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("));\n");
		}
		
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
		
		if(forexpression.isFunctionCall()){
			CubeXBlock flattened = new CubeXBlock();
			
			CubeXExpression originalExpression = CubeXFunctionCall.copy((CubeXFunctionCall)forexpression);
			
			CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), originalExpression);
			this.forexpression = tempVar.getVariable();
			flattened.add(tempVar);
			flattened.add(this);	
			GlobalAwareness.notFlattened = true;		
			return flattened;
		}
		
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
		ExpressionContext forCon = forbody.eliminateCommonSubexpressions(con.createChildContext());
		ExpressionContext addToLocalCon = new ExpressionContext(null);
		forCon.merge(addToLocalCon, con);
		
		con.addAll(addToLocalCon);
		
		return con;
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
