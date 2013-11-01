package main.statement;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.CubeXType;
import main.util.Tuple;

public class CubeXReturnStatement extends CubeXStatement {

	private CubeXExpression returnValue;
	
	public CubeXReturnStatement(CubeXExpression returnValue)
	{
		this.returnValue = returnValue;
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon, FunctionContext funCon,	VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par)	throws ContextException, TypeCheckException {
		CubeXType type = returnValue.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		return new Tuple<Boolean, CubeXType>(true, type);
	}

	@Override
	public String preC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("return ").append(returnValue.toString()).append(" ;");
		return sb.toString();
	}

}
