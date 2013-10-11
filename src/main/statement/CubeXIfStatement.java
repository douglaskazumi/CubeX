package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXIfStatement extends CubeXStatement {

	private CubeXExpression condition;
	private CubeXStatement ifstatement;
	private CubeXStatement elsestatement;
	
	public CubeXIfStatement(CubeXExpression condition, CubeXStatement ifstatement, CubeXStatement elsestatement)
	{
		this.condition = condition;
		this.ifstatement = ifstatement;
		this.elsestatement = elsestatement;
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
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException,TypeCheckException {
		
		CubeXType condType = condition.getType(classCon, funCon, varCon, typeVarCon);
		if(!condType.isBool())
			throw new TypeCheckException();
		
		boolean mutable = varCon.isMutable();
		varCon.setMutable(false);;
		VariableContext innerConTrue = (VariableContext)varCon.createChildContext();
		VariableContext innerConFalse = (VariableContext)varCon.createChildContext();
		
		Tuple<Boolean, CubeXType> resTrue = ifstatement.typecheck(classCon, funCon, innerConTrue, typeVarCon);
		Tuple<Boolean, CubeXType> resFalse = ifstatement.typecheck(classCon, funCon, innerConTrue, typeVarCon);
		
		varCon.setMutable(mutable);
		
		VariableContext.merge(varCon, innerConTrue, innerConFalse, classCon, typeVarCon);
		
		if(!resTrue.first)
			return new Tuple<Boolean, CubeXType>(false, null);
		if(!resFalse.first)
			return new Tuple<Boolean, CubeXType>(false, null);
		
		CubeXType returnType = CubeXType.join(resTrue.second, resFalse.second, classCon, typeVarCon);
		
		return new Tuple<Boolean, CubeXType>(true, returnType) ;
	}
}
