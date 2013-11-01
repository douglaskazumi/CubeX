package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.expression.CubeXIterable;
import main.expression.CubeXVariable;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.util.Tuple;

public class CubeXForStatement extends CubeXStatement {

	private String variable;
	private CubeXVariable actualVariable;
	private CubeXExpression forexpression;
	private CubeXStatement forbody;
	
	public CubeXForStatement(String variable, CubeXExpression forexpression, CubeXStatement forbody)
	{
		this.variable = variable;
		this.actualVariable=new CubeXVariable(variable);
		this.forbody = forbody;
		this.forexpression = forexpression;
	}

	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		CubeXType forExprType = forexpression.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(!forExprType.isIterable())
			throw new TypeCheckException();
		CubeXTypeIterable forIterable = (CubeXTypeIterable)forExprType;
		CubeXType innerType=forIterable.getInnerType();		
		boolean mutable = varCon.isMutable();
		VariableContext innerCon = (VariableContext)varCon.createChildContext();
		innerCon.add(variable, innerType);
		forbody.typecheck(force, classCon, funCon, innerCon, typeVarCon, false, par);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}

	@Override
	public String preC() {
		StringBuilder sb = new StringBuilder();
		sb.append("int ").append(variable).append(";\n");
		sb.append(forexpression.preC()).append(";\n");
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("for ( ").append(variable).append(" in ").append(forexpression.toString()).append(" ) ").append(forbody.toString());
		return sb.toString();
	}

	
}
