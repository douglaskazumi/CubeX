package main.context;

import java.util.HashMap;

import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;

public class VariableContext extends BaseContext<String, CubeXType> {

	public VariableContext(VariableContext p)
	{
		super(p);
	}
	
	public VariableContext createChildContext()
	{
		return new VariableContext(this);
	}

	public static void merge(VariableContext varCon, VariableContext innerConTrue, VariableContext innerConFalse, ClassContext classCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException
	{
		for(String id : innerConTrue.context.keySet())
		{
			if(!innerConFalse.context.containsKey(id))
				continue;
			CubeXType typeA = innerConTrue.context.get(id);
			CubeXType typeB = innerConFalse.context.get(id);
			CubeXType mergedType = CubeXType.join(typeA, typeB, classCon);
			
			varCon.add(id, mergedType);
		}
	}
	public HashMap<String, CubeXType> getInnerMap()
	{
		return this.context;
	}

	public void lockVariables()
	{
		setMutable(false);
	}
}
