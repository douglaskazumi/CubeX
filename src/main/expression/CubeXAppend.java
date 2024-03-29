package main.expression;

import java.util.HashSet;

import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXAssignment;
import main.statement.CubeXBlock;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;


public class CubeXAppend extends CubeXExpression {

	private CubeXExpression a;
	private CubeXExpression b;
	
	public CubeXAppend(CubeXExpression a, CubeXExpression b) {
		this.a=a;
		this.b=b;
	}

	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) throws ContextException, TypeCheckException {
		CubeXType typeA=a.getType(force, classCon,funCon,varCon,typeVarCon, setField, par, parFunction);
		CubeXType typeB=b.getType(force, classCon,funCon,varCon,typeVarCon, setField, par, parFunction);
		if (typeA.equals(CubeXType.getString()))
			typeA=new CubeXTypeIterable(CubeXType.getCharacter());
		if (typeB.equals(CubeXType.getString()))
			typeB=new CubeXTypeIterable(CubeXType.getCharacter());
		CubeXType joinedType=CubeXType.join(typeA, typeB, classCon);
		if (joinedType.isIterable())
		{
			return joinedType;
		}
		else
		{
			throw new TypeCheckException("Bad append types - not iterables");
		}
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append("iterableAppend(").append(a.toC(par)).append(", ").append(b.toC(par)).append(")");
		
		
		temps.addAll(a.temps);
		temps.addAll(b.temps);
		return sb.toString();
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(a.preC(par)).append(b.preC(par));		
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(a.toString()).append(" ++ ").append(b.toString());
		return sb.toString();
	}

	@Override
	public HashSet<CubeXVariable> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		HashSet<CubeXVariable> vars = new HashSet<>();
		vars.addAll(a.getUsedVars(globals, ignoredFunctions));
		vars.addAll(b.getUsedVars(globals, ignoredFunctions));
		return vars;
	}

	@Override
	public CubeXExpression reduceBoxes()
	{
		a=a.reduceBoxes();
		b=b.reduceBoxes();
		return this;
	}

	@Override
	public CubeXExpression addBoxes()
	{
		a=a.addBoxes();
		b=b.addBoxes();
		return this;
	}

	@Override
	public CubeXExpression simplifyFunctionBoxes() {
		a=a.simplifyFunctionBoxes();
		b=b.simplifyFunctionBoxes();
		return this;
	}

	@Override
	public CubeXExpression primitivifyVariables() {
		a=a.primitivifyVariables();
		b=b.primitivifyVariables();
		return this;
	}	
	
	@Override
	public boolean isAppend() {
		return true;
	}

	public static CubeXAppend copy(CubeXAppend original) {
		return new CubeXAppend(original.a, original.b);
	}
	
	public CubeXStatement flatten(){
		CubeXBlock flattened = new CubeXBlock();		

		if(a.isAppend()||a.isIterable()||a.isFunctionCall()){
			CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), a);
			a = tempVar.getVariable();
			flattened.add(tempVar);
			GlobalAwareness.notFlattened = true;
		}
		if(b.isAppend()||b.isIterable()||b.isFunctionCall()){
			CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), b);
			b = tempVar.getVariable();
			flattened.add(tempVar);
			GlobalAwareness.notFlattened = true;
		}
		
		return flattened;
	}

	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXAppend))
			return false;
		
		if(((CubeXAppend) other).isAppend()){
			CubeXAppend oA = (CubeXAppend)other;
			return a.equals(oA.a) && b.equals(oA.b);
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}
	
	@Override
	public boolean contains(CubeXVariable var) {
		return a.contains(var) || b.contains(var);
	}
	
	@Override
	public void replace(CubeXVariable oldVar, CubeXExpression newVar) {
		a.replace(oldVar, newVar);
		b.replace(oldVar, newVar);
	}

	public boolean hasFunctionCall() {
		return a.isFunctionCall() || b.isFunctionCall();
	}
}
	
