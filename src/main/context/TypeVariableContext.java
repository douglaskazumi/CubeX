package main.context;

import main.type.CubeXTypeVariable;

public class TypeVariableContext extends BaseContext<CubeXTypeVariable> {

	public TypeVariableContext(TypeVariableContext p)
	{
		super(p);
		setMutable(false);
	}

	public TypeVariableContext createChildContext()
	{
		return new TypeVariableContext(this);
	}
}
