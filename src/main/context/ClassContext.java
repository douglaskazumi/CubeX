package main.context;

import main.program.CubeXClassBase;

public class ClassContext extends BaseContext<CubeXClassBase> {

	public ClassContext(ClassContext p) {
		super(p);
	}
	
	public ClassContext createChildContext()
	{
		return new ClassContext(this);
	}
}
