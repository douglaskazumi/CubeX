package main.expression;

import java.util.HashSet;

import main.Optimizations.Boxer;
import main.c.CUtils;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.GlobalContexts;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.util.CubeXArgument;


public class CubeXVariable extends CubeXExpression 
{
	private String name;
	private boolean isField;
	public boolean isLocal;
	private CubeXClass parent;
	public boolean isPrimitive;
	
	public CubeXVariable(String name)
	{
		this.name=name;
		isField=false;
		parent=null;
	}
		
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, CubeXFunction parFunction) throws ContextException, TypeCheckException
	{
		CubeXType varType= varCon.lookup(name);
		if(varType==null)
			throw new ContextException("Variable not in context");
		trySetField(setField, par, parFunction);
		isLocal = (GlobalContexts.variableContext.lookup(name)==null && par!=null) || isField;
		
		return varType;
	}
	
	public String getName() {
		return name;
	}

	public boolean isField() {
		return isField;
	}
	
	public boolean isVariable() {
		return true;
	}

	private void setIsField(CubeXProgramPiece p)
	{
		parent=(CubeXClass) p;
		isField=true;
	}

	public void trySetField(boolean setField, CubeXProgramPiece par, CubeXFunction parFunction)
	{
		if(par!=null && par.isFunction())
			return;
		
		if(setField)
		{
			this.setIsField(par);
			if(par!=null && par.isClass())
			{
				CubeXClass clss = (CubeXClass)par;
				if(!clss.definedFields.contains(name))
				{
					clss.definedFields.add(name);
				}
			}
		}
		else
		{
			if(par!=null && par.isClass())
			{
				if(parFunction!=null)
				{
					for(CubeXArgument arg : parFunction.getArglist())
					{
						if(arg.variable.getName().equals(name))
							return;
					}
				}
				if(((CubeXClass)par).definedFields.contains(name))
					this.setIsField(par);
			}
		}
	}

	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		if(isField)
		{				
			
			sb.append("*(((object_t **)(this+1))+").append(parent.definedFields.lastIndexOf(name)).append(")");
		}
		else
		{
			sb.append(CUtils.canonName(name));
		}
		return sb.toString();
	}

	public String toString()
	{
		return name;
	}
	
	@Override
	public HashSet<CubeXVariable> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) {
		HashSet<CubeXVariable> vars = new HashSet<CubeXVariable>();
		if((!globals && this.isLocal) || (globals && !this.isLocal && !this.isField()))
			vars.add(this);
		return vars;
	}

	@Override
	public CubeXExpression reduceBoxes() {
		return this;
	}
	
	@Override
	public CubeXExpression addBoxes() {
		return this;
	}
	
	@Override
	public CubeXExpression simplifyFunctionBoxes()
	{
		return this;
	}

	@Override
	public CubeXExpression primitivifyVariables()
	{
		if((getTypeUnsafe().isInt() || getTypeUnsafe().isBool()) && !isField)
		{
			isPrimitive=true;
			return Boxer.boxify(this);
		}
		return this;
	}
	
//	@Override
//	public boolean equals(CubeXExpression other) {
//		if(other != null && other.isVariable()){
//			CubeXVariable oV = (CubeXVariable)other;
//			return name.equals(oV.name) && (parent == null ? oV.parent == null : parent.equals(oV.parent));
//		}
//
//		return false;
//	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXVariable))
			return false;
		if(((CubeXExpression)other).isVariable()){
			CubeXVariable oV = (CubeXVariable)other;
			return name.equals(oV.name);
		}

		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
