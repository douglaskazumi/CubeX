package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.expression.CubeXVariable;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.util.Tuple;

public class CubeXAssignment extends CubeXStatement {

	private String name;
	private CubeXVariable variable;
	private CubeXExpression expr;
	
	public CubeXAssignment(String name,CubeXExpression expr)
	{
		this.name=name;
		this.expr=expr;
		this.variable=new CubeXVariable(name);
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException {
		
		CubeXType type = expr.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(type.isVariable() && (typeVarCon.lookup(((CubeXTypeVariable)type).getName())==null))
			throw new ContextException();
		
		variable.trySetField(setField, par);
		if(par!=null && par.isClass())
			((CubeXClass) par).definedFields.add(name);
		
		varCon.add(name, type);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}

	@Override
	public String preC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toC(CubeXProgramPiece par) 
	{
		if(!variable.isField())
			par.addLocal(name);
		return null;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" := ").append(expr.toString()).append(" ;");
		return sb.toString();
	}

	
}
