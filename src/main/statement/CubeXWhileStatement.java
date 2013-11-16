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

public class CubeXWhileStatement extends CubeXStatement 
{
	private CubeXExpression condition;
	private CubeXStatement whilestatement;
	
	public CubeXWhileStatement(CubeXExpression condition, CubeXStatement whilestatement)
	{
		this.condition = condition;
		this.whilestatement = whilestatement;
	}
	
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		CubeXType condType = condition.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
		if(!condType.isBool())
			throw new TypeCheckException("While condition not a bool");
		boolean mutable = varCon.isMutable();
		whilestatement.typecheck(force, classCon, funCon, (VariableContext) varCon.createChildContext(), typeVarCon, false, par);
		varCon.setMutable(mutable);
		
		return new Tuple<Boolean, CubeXType>(false, null);
	}


	@Override
	public String preC(CubeXProgramPiece par) {
		return "";
	}


	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("while(true)\n\t{\n");
		sb.append(condition.preC(par));
		sb.append("\tif(!isTrue(").append(condition.toC(par)).append("))\n\t");
		sb.append("{\n");
		sb.append(condition.postC(par));
		sb.append("\t\tbreak;\n\t}\n");
		sb.append(condition.postC(par)).append("\n");	
		sb.append("\t\t").append(whilestatement.preC(par));
		sb.append("\t\t").append(whilestatement.toC(par));
		sb.append("\t}\n");
		return sb.toString();
	}


	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("while ( ").append(condition.toString()).append(" ) ").append(whilestatement.toString());
		return sb.toString();
	}


	@Override
	public void initializeSucc(CubeXStatement after)
	{
		addSucc(this.whilestatement);
		whilestatement.initializeSucc(this);
		addSucc(after);
		
	}


	@Override
	public void initializeUsedVariables() {
		// TODO Auto-generated method stub
		
	}
	
}
