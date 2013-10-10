package main.context;

import main.program.*;

public class FunctionContext extends BaseContext<CubeXFunction> {

	public FunctionContext(FunctionContext p) {
		super(p);
		setMutable(false);
	}

}
