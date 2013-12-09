package main.program;

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
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.util.CubeXArgument;
import main.util.Tuple;

public class CubeXYielder extends CubeXProgramPiece {
	
	private CubeXStatement stat;
	
	private CubeXClassYielder parent;
	
	private ArrayList<Integer> internalYields = new ArrayList<>();

	public void addYield(Integer yieldId)
	{
		internalYields.add(yieldId);
	}
	
	public CubeXYielder(CubeXStatement stat)
	{
		this.stat=stat;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException, TypeCheckException
	{
		if(par instanceof CubeXClassYielder)
			parent = (CubeXClassYielder)par;
		
		return stat.typecheck(force, classCon, funCon, varCon, typeVarCon, setField, par, true);
	}

	@Override
	public String preC() {
		return stat.preC(this);
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbdecl = new StringBuilder();

		sbdecl.append("yielder_").append(parent.getName()).append("_t * ").append("y_").append(parent.getName()).append("(yielder_").append(parent.getName()).append("_t *yielder, object_t *this)");

		sb.append(sbdecl.toString()).append("\n {\n").append(System.lineSeparator());
		
		GlobalAwareness.declarationAppend(sbdecl.append(";\n").toString());
		
		HashSet<String> strArgs = new HashSet<String>();

		for(String var :locals.keySet())
		{
			sb.append("\tobject_t * ").append(CUtils.canonName(var)).append(" = NULL;\n");
		}
		
		for (String var : locals.keySet()) {
			sb.append("object_t *").append(CUtils.canonName(var)).append(" = ").append("yielder->").append(CUtils.canonName(var)).append(";").append(System.lineSeparator());
		}
		
		sb.append("switch(yielder->status){").append(System.lineSeparator());
		sb.append("case 0: break;").append(System.lineSeparator());
		
		for(Integer yieldId : internalYields){
			sb.append("case ").append(yieldId).append(": goto LABEL_").append(yieldId).append(";").append(System.lineSeparator());
		}
		sb.append("}").append(System.lineSeparator());
		
		sb.append(stat.toC(this));
		
		sb.append("yielder->status = -1;").append(System.lineSeparator());
		sb.append("return yielder;").append(System.lineSeparator());
		
		sb.append("}").append(System.lineSeparator());
		
		return sb.toString();
	}

	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece next,
			boolean isTopLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initializeUsedVariables(boolean onlyGlobals,
			HashSet<CubeXFunction> ignoredFunctions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeadVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBoxes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void simplifyFunctionBoxes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void primitivifyVariables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduceBoxes() {
		
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con)
			throws ContextException {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append("yielder ").append(stat==null?";":stat.toString());
		return sb.toString();
	}
	
}
