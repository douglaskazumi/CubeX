package main.context;

import java.util.Collection;

import main.program.CubeXClassBase;

public class ClassContext extends BaseContext<CubeXClassBase> {

	public ClassContext(ClassContext p) {
		super(p);
	}
	
	public ClassContext createChildContext()
	{
		return new ClassContext(this);
	}
	
	public Collection<CubeXClassBase> getAllClasses()
	{
		return this.context.values();
	}
}
