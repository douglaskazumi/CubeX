package main.expression;
import java.util.ArrayList;









import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.type.*;

public class CubeXIterable extends CubeXExpression
{
	ArrayList<CubeXExpression> entries;
	
	public CubeXIterable(ArrayList<CubeXExpression> entries) 
	{
		this.entries = entries;
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException
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
		sbpost.append("NULL");
		for(CubeXExpression entry : entries)
		{
			sbpre.append("iterableAppend(createIterable_value(").append(entry.toC(par)).append(", 0), ");
			sbpost.append(")");
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
}
