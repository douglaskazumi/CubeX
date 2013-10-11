package main.type;

import java.util.ArrayList;

import main.context.ClassContext;
import main.context.TypeVariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXFunction;
import main.util.Tuple;
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
	public boolean isSpecial()
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
	
	public abstract boolean equals(CubeXType other);
	
	
	
	public static CubeXType join(CubeXType a, CubeXType b, ClassContext classCon, TypeVariableContext typeVarCon) {
		return null;
		//TODO 
	}
	
	public static CubeXType makeSubstitution(CubeXType type,TypeVarSubstitution sub)
	{
		if(type.isVariable())
		{
			CubeXType res = sub.get((CubeXTypeVariable)type);
			if(res==null)
				return type;
			return res;
		}
		
		if(type.isIntersection())
		{
			CubeXTypeIntersection intersection = (CubeXTypeIntersection)type; 
			return new CubeXTypeIntersection(makeSubstitution(intersection.left, sub), makeSubstitution(intersection.right, sub));
		}

		return type;
	}
	
	public static void validateType(CubeXType type, boolean isInExtends, ClassContext classCon, TypeVariableContext typeVarCon) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Checks if type 1 is subtype of type2
	 * @param type1
	 * @param type2
	 * @return True if it is.
	 */
	public static boolean isSubType(CubeXType type1, CubeXType type2)
	{
		if(type1.isNothing())
			return true;
		if(type1.isThing())
			return type2.isThing();
		if(type2.isNothing())
			return false;
		if(type2.isThing())
			return true;
		
		if(type1.isIntersection())
		{
			CubeXTypeIntersection intersection1 = (CubeXTypeIntersection)type1;
			return isSubType(intersection1.left, type2) || isSubType(intersection1.right, type2);
		}
		
		if(type2.isIntersection())
		{
			CubeXTypeIntersection intersection2 = (CubeXTypeIntersection)type2;
			return isSubType(type1, intersection2.left) && isSubType(type1, intersection2.right);
		}
		
		if(type1.isIterable())
		{
			if(type2.isIterable())
			{
				CubeXTypeIterable iterable1 = (CubeXTypeIterable)type1;
				CubeXTypeIterable iterable2 = (CubeXTypeIterable)type2;
				return isSubType(iterable1.getInnerType(), iterable2.getInnerType());
			}
			
			return false;
		}
		if(type2.isIterable())
			return false;
	
		if(type1.isVariable())
		{
			if(type2.isVariable())
			{
				CubeXTypeVariable var1 = (CubeXTypeVariable)type1;
				CubeXTypeVariable var2 = (CubeXTypeVariable)type2;
				return var1.equals(var2);
			}
			return false;
		}
		if(type2.isVariable())
			return false;
		
		CubeXTypeClassBase base1 = (CubeXTypeClassBase)type1;
		CubeXTypeClassBase base2 = (CubeXTypeClassBase)type2;
		
		for(CubeXType base1Type : getSuperTypes(base1))
		{
			if(base1Type.equals(base2))
				return true;
		}
		
		return false;
	}




	public static ArrayList<CubeXType> getSuperTypes(CubeXType parentType) {
		ArrayList<CubeXType> supers =new ArrayList<CubeXType>();
		
		if(parentType.isClass()||parentType.isInterface()||parentType.isVariable())
		{
			supers.add(parentType);
			return supers;
		}
		else if(parentType.isIntersection())
		{
			supers.addAll(getSuperTypes(((CubeXTypeIntersection)parentType).left));
			supers.addAll(getSuperTypes(((CubeXTypeIntersection)parentType).right));
			return supers;
		} else
		{
			return supers;
		}
	}

}

