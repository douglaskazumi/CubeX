package main.context;

import main.exceptions.ContextException;
import main.type.CubeXType;

public class VariableContext extends BaseContext<CubeXType> {

	public VariableContext(VariableContext p)
	{
		super(p);
	}
	
	public static void merge(VariableContext varCon, VariableContext innerConTrue, VariableContext innerConFalse, ClassContext classCon, TypeVariableContext typeVarCon) throws ContextException
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
	public VariableContext createChildContext()
	{
		return new VariableContext(this);
	}
	
	public void lockVariables()
	{
		setMutable(false);
	}
}
