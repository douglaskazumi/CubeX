package main.type;

import main.context.ClassContext;
import main.context.TypeVariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.TypeVarSubstitution;

public abstract class CubeXType 
{

	
	public static CubeXTypeBoolean cubeXBool = new CubeXTypeBoolean();
	public static CubeXTypeBoolean getBoolean()
	{
		return cubeXBool;
	}
	
	public static CubeXTypeInteger cubeXInt = new CubeXTypeInteger();
	public static CubeXTypeInteger getInteger()
	{
		return cubeXInt;
	}
	
	public static CubeXTypeCharacter cubeXChar = new CubeXTypeCharacter();
	public static CubeXTypeCharacter getCharacter()
	{
		return cubeXChar;
	}	
	
	public static CubeXTypeSpecial cubeXThing = new CubeXTypeSpecial(true);
	public static CubeXTypeSpecial getThing()
	{
		return cubeXThing;
	}	
	
	public static CubeXTypeSpecial cubeXNothing = new CubeXTypeSpecial(false);
	public static CubeXTypeSpecial getNothing()
	{
		return cubeXNothing;
	}	
	
	public static CubeXTypeString cubeXString = new CubeXTypeString();
	public static CubeXTypeString getString()
	{
		return cubeXString;
	}	
	
	
	//TODO finish this!
	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		return ((CubeXType)other).equals(this);
	}	
	
	public abstract Tuple<TypeVarSubstitution,CubeXFunction> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException;
	
	public boolean isBool()
	{
		return false;
	}
	public boolean isInt()
	{
		return false;
	}
	public boolean isChar()
	{
		return false;
	}
	public boolean isClass()
	{
		return false;
	}
	public boolean isInterface()
	{
		return false;
	}
	public boolean isIntersection()
	{
		return false;
	}
	public boolean isThing()
	{
		return false;
	}
	public boolean isNothing()
	{
		return false;
	}
	public boolean isVariable()
	{
		return false;
	}
	public boolean isIterable()
	{
		return false;
	}
	
	public boolean isExtendable()
	{
		return true;
	}
	
	public CubeXType getConstructableComponent()
	{
		return CubeXType.getThing();
	}
	
	
	public static CubeXType join(CubeXType a, CubeXType b, ClassContext classCon, TypeVariableContext typeVarCon) {
		return null;
		//TODO 
	}
	
	
	public static CubeXType makeSubstitution(CubeXType type,TypeVarSubstitution sub)
	{
		//TODO!!
		return null;
	}

	/**
	 * Checks if type 1 is subtype of type2
	 * @param type1
	 * @param type2
	 * @return True if it is.
	 */
	public static boolean isSubType(CubeXType type1, CubeXType type2) {
		// TODO Auto-generated method stub
		return false;
	}


	public static void validateType(CubeXType type,	ClassContext classCon, TypeVariableContext typeVarCon) {
		// TODO Auto-generated method stub
		
	}

	// TODO
//	private array of classbase getParentSet(type)
}

