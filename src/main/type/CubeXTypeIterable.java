package main.type;

import java.util.ArrayList;
import java.util.Arrays;

public class CubeXTypeIterable extends CubeXTypeClass
{

	public CubeXTypeIterable(CubeXType parameter) {
		super("Iterable", new ArrayList<CubeXType>(Arrays.asList(parameter)));
	}
	
	public boolean isIterable()
	{
		return true;
	}
	
	public boolean isExtendable()
	{
		return false;
	}
	
}