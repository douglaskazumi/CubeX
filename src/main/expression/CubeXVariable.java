package main.expression;

import main.c.CUtils;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.type.CubeXType;


public class CubeXVariable extends CubeXExpression 
{
	private String name;
	private boolean isField;
	private CubeXClass parent;
	
	public CubeXVariable(String name)
	{
		this.name=name;
		isField=false;
		parent=null;
	}
		
	public String toString()
	{
		return name;
	}
	
	private void setIsField(CubeXClassBase p)
	{
		parent=(CubeXClass) p;
		isField=true;
	}

	@Override
	protected CubeXType calculateType(ClassContext classCon,FunctionContext funCon, VariableContext varCon,	TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException
	{
		CubeXType varType= varCon.lookup(name);
		if(varType==null)
			throw new ContextException("Variable not in context");
		trySetField(setField, par);
		return varType;
	}
	
	public void trySetField(boolean setField, CubeXClassBase par)
	{
		if(setField)
		{
			this.setIsField(par);
		}
		else
		{
			if(par!=null && par.isClass())
			{
				if(((CubeXClass)par).definedFields.contains(name))
					this.setIsField(par);
			}
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public String preC() {
		return "";
	}

	
	
	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		if(isField)
		{
			sb.append("(((").append(CUtils.canonStruct(parent)).append("*)this)->").append(name).append(")");
		}
		else
		{
			sb.append("v_").append(name);
		}
		return sb.toString();
	}
}
