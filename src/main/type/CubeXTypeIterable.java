package main.type;

import java.util.ArrayList;
import java.util.Arrays;

public class CubeXTypeIterable extends CubeXTypeClass
{
	private CubeXType innerType;

	public CubeXTypeIterable(CubeXType parameter) {
		super("Iterable", new ArrayList<CubeXType>(Arrays.asList(parameter)));
		innerType=parameter;
	}
	
	public boolean isIterable()
	{
		return true;
	}
	
	public boolean isExtendable()
	{
		return false;
	}

	public CubeXType getInnerType() {
		return innerType;
	}
	
}