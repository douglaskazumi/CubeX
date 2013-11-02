package main.statement;

import main.c.CUtils;
import main.c.GlobalAwareness;
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
	private CubeXExpression forexpression;
	private CubeXStatement forbody;
	private String indexer;
	private String iterable;
	
	public CubeXForStatement(String variable, CubeXExpression forexpression, CubeXStatement forbody)
	{
		this.variable = variable;
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
	public String preC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		sb.append(forexpression.preC());
		indexer = CUtils.getTempName();
		iterable = CUtils.getTempName();
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		if(par!=null)
		{
			par.addLocal(indexer);
			par.addLocal(iterable);
			par.addLocal(variable);
		}
		else
		{
			GlobalAwareness.addLocal(indexer);
			GlobalAwareness.addLocal(iterable);
			GlobalAwareness.addLocal(variable);
		}
		sb.append(CUtils.canonName(indexer)).append(" = (object_t *)createIndexer();\n");
		sb.append("\t").append(CUtils.canonName(iterable)).append(" = ").append(forexpression.toC()).append(";\n");
		sb.append("\twhile(iterableHasNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append("))\n\t{\n");
		String pre = forbody.preC(par);
		if(!pre.isEmpty())
			sb.append("\t\t").append(pre);
		sb.append("\t\t").append(CUtils.canonName(variable)).append(" = iterableNext(").append(CUtils.canonName(iterable)).append(", (iterableIndex_t *)").append(CUtils.canonName(indexer)).append(");\n");
		sb.append("\t\t").append(forbody.toC(par));
		sb.append("\t}\n");
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("for ( ").append(variable).append(" in ").append(forexpression.toString()).append(" ) ").append(forbody.toString());
		return sb.toString();
	}

	
}
