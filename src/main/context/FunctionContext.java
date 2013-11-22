package main.context;

import main.program.*;

public class FunctionContext extends BaseContext<String, CubeXFunction> {

	public FunctionContext(FunctionContext p) {
		super(p);
		setMutable(false);
	}

	public FunctionContext createChildContext()
	{
		return new FunctionContext(this);
	}
}
