package main.yields;

import main.type.CubeXType;
import main.type.CubeXTypeClass;

import java.util.ArrayList;
import java.util.Arrays;

public class CubeXTypeYielder extends CubeXTypeClass
{
	private CubeXType innerType;
	
	public CubeXTypeYielder(String name, CubeXType parameter) {
		super(name, new ArrayList<CubeXType>(Arrays.asList(parameter)));
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
	
	@Override
	public boolean isYielder()
	{
		return true;
	}
	
	public CubeXType getInnerType() {
		return innerType;
	}
	
}
