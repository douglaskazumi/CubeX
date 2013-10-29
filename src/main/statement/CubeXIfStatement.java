package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
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
		if(elsestatement==null)
			elsestatement=new CubeXBlock();
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
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException {
		
		CubeXType condType = condition.getType(classCon, funCon, varCon, typeVarCon, setField, par);
		if(!condType.isBool())
			throw new TypeCheckException();
		
		boolean mutable = varCon.isMutable();
		VariableContext innerConTrue = (VariableContext)varCon.createChildContext();
		VariableContext innerConFalse = (VariableContext)varCon.createChildContext();
		
		Tuple<Boolean, CubeXType> resTrue = ifstatement.typecheck(classCon, funCon, innerConTrue, typeVarCon, false, null);
		Tuple<Boolean, CubeXType> resFalse = elsestatement.typecheck(classCon, funCon, innerConFalse, typeVarCon, false, null);
		
		varCon.setMutable(mutable);
		
		if(par!=null && par.isClass())
		{
			VariableContext testCon = new VariableContext(null);
			VariableContext.merge(testCon, innerConTrue, innerConFalse, classCon, typeVarCon);
			for(String varName : testCon.getInnerMap().keySet())
			{
				((CubeXClass)par).definedFields.add(varName);
			}
			
			
			innerConTrue.getInnerMap().clear();
			innerConFalse.getInnerMap().clear();
			
			resTrue = ifstatement.typecheck(classCon, funCon, innerConTrue, typeVarCon, setField, par);
			resFalse = elsestatement.typecheck(classCon, funCon, innerConFalse, typeVarCon, setField, par);
			
			varCon.setMutable(mutable);
		
		}
		
		VariableContext.merge(varCon, innerConTrue, innerConFalse, classCon, typeVarCon);
		
		if(!resTrue.first)
			return new Tuple<Boolean, CubeXType>(false, null);
		if(!resFalse.first)
			return new Tuple<Boolean, CubeXType>(false, null);
		
		CubeXType returnType = CubeXType.join(resTrue.second, resFalse.second,classCon);
		
		return new Tuple<Boolean, CubeXType>(true, returnType) ;
	}
}
