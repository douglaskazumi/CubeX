package main.expression;
import java.util.ArrayList;










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
import main.type.*;

public class CubeXIterable extends CubeXExpression
{
	ArrayList<CubeXExpression> entries;
	
	public CubeXIterable(ArrayList<CubeXExpression> entries) 
	{
		this.entries = entries;
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par) throws ContextException, TypeCheckException
	{
		CubeXType curType=CubeXType.getNothing();
		for(CubeXExpression exp : entries)
		{
			curType = CubeXType.join(curType, exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), classCon);
		}
		return new CubeXTypeIterable(curType);
	}

	@Override
	public String preC(CubeXProgramPiece par)
	{
		StringBuilder sb = new StringBuilder();
		for(CubeXExpression entry : entries)
		{
			sb.append(entry.preC(par));
		}
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sbpre = new StringBuilder();
		StringBuilder sbpost = new StringBuilder();
		if(entries.size()==0)
			return "NULL";
		int i=1;
		for(CubeXExpression entry : entries)
		{
			if(i!=entries.size())
			{
				sbpre.append("iterableAppend(createIterable_value(").append(entry.toC(par)).append(", 0), ");
			}
			else
			{
				sbpre.append("createIterable_value(").append(entry.toC(par)).append(", 0");
			}
			sbpost.append(")");
			i++;
		}
		
		for(CubeXExpression expr : entries)
		{
			temps.addAll(expr.temps);
		}
		
		return sbpre.append(sbpost).toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		String prefix="";
		for (CubeXExpression expr : entries)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public HashSet<CubeXVariable> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		HashSet<CubeXVariable> vars = new HashSet<CubeXVariable>();
		for(CubeXExpression expr : entries)
		{
			vars.addAll(expr.getUsedVars(globals, ignoredFunctions));
		}
		return vars;
	}

	@Override
	public CubeXExpression reduceBoxes()
	{
		for(int i=0; i<entries.size(); ++i)
		{
			CubeXExpression newEntry = entries.get(i).reduceBoxes();
			entries.set(i, newEntry);
		}
		return this;
	}
	
	@Override
	public CubeXExpression addBoxes() 
	{
		for(int i=0; i<entries.size(); ++i)
		{
			CubeXExpression newEntry = entries.get(i).addBoxes();
			entries.set(i, newEntry);
		}
		return this;	
	}

	@Override
	public CubeXExpression simplifyFunctionBoxes() {
		for(int i=0; i<entries.size(); ++i)
		{
			CubeXExpression newEntry = entries.get(i).simplifyFunctionBoxes();
			entries.set(i, newEntry);
		}
		return this;	
	}
	
	@Override
	public CubeXExpression primitivifyVariables() {
		for(int i=0; i<entries.size(); ++i)
		{
			CubeXExpression newEntry = entries.get(i).primitivifyVariables();
			if(newEntry==entries.get(i))
				continue;
			entries.set(i, newEntry);
		}
		return this;	
	}
	
	@Override
	public boolean isIterable() {
		return true;
	}

	public static CubeXIterable copy(CubeXIterable original) {
		return new CubeXIterable(original.entries);
	}
	
	public CubeXStatement flatten(){
		CubeXBlock flattened = new CubeXBlock();	
		ArrayList<CubeXExpression> flattenedEntries = new ArrayList<>();
		
		for(CubeXExpression entry : entries){
			if(entry.isFunctionCall()){
				CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), entry);
				flattened.add(tempVar);
				flattenedEntries.add(tempVar.getVariable());
			}
			else{
				flattenedEntries.add(entry);
			}
		}
		entries = flattenedEntries;
		
		return flattened;
	}
	
	@Override
	public boolean equals(CubeXExpression other) {
		if(other != null && other.isIterable()){
			CubeXIterable oI = (CubeXIterable)other;
			if(entries.size() != oI.entries.size())
				return false;
			
			for(int i = 0; i < entries.size(); i++)
				if(!entries.get(i).equals(oI.entries.get(i)))
					return false;
			
			return true;
		}

		return false;
	}
	
	@Override
	public boolean contains(CubeXVariable var) {
		return entries.contains(var);
	}
}
