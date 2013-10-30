package main.type;

import java.util.ArrayList;

import main.context.ClassContext;
import main.context.TypeVariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.util.Triple;
import main.util.TypeVarSubstitution;

public abstract class CubeXType
{
	public String getTypedefName() {
		return this.toString().toLowerCase() + "_t";
	}
	
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
	
	public abstract Triple<TypeVarSubstitution,CubeXFunction, CubeXTypeClassBase> methodLookup(String name, ClassContext classCon) throws ContextException, TypeCheckException;
	
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
	
	public boolean isString()
	{
		return false;
	}
	
	public CubeXType getConstructableComponent()
	{
		return CubeXType.getThing();
	}
	
	public abstract boolean equals(CubeXType other);
	
	public boolean equals(Object other)
	{
		return equals((CubeXType)other);
	}
	
	
	
	public static CubeXType join(CubeXType a, CubeXType b, ClassContext classCon) throws ContextException {
		if(a.isNothing())
			return b;
		if(b.isNothing())
			return a;
		if(a.isThing() || b.isThing())
			return CubeXType.getThing();
		
		if(a.isString()&&b.isString())
			return CubeXType.getString();
			
		if(a.isIterable() && b.isIterable())
		{
			return new CubeXTypeIterable(join(((CubeXTypeIterable)a).getInnerType(),((CubeXTypeIterable)b).getInnerType(), classCon));
		}
		
		ArrayList<CubeXType> aSupers = getSuperTypes(a, classCon);
		ArrayList<CubeXType> intersection = new ArrayList<CubeXType>();
		ArrayList<CubeXType> aux = getSuperTypes(b,  classCon);
		
		for(CubeXType aS : aSupers){
			if(aux.contains(aS)){
				intersection.add(aS);
			}
		}
					
		aux = new ArrayList<CubeXType>();
		
		for(CubeXType c : intersection){
			for(CubeXType p : intersection){
				if(!c.equals(p) && isSubType(c, p, classCon))
					if(!aux.contains(p))
						aux.add(p);
			}
		}
		
		intersection.removeAll(aux);
		
		if(intersection.size() == 0)
			return getThing();
		if(intersection.size() == 1)
			return intersection.get(0);		
		
		CubeXType firstElement = intersection.get(0);
		for(CubeXType c : intersection){
			if(c.isClass()){
				int index = intersection.indexOf(c);
				firstElement = intersection.get(0);
				intersection.set(0, c);
				intersection.set(index, firstElement);
				break;
			}
		}
		
		CubeXTypeIntersection join = new CubeXTypeIntersection(intersection.get(0),intersection.get(1));
		for(int i = 2; i < intersection.size(); i++ ){
			join = new CubeXTypeIntersection(join,intersection.get(i));
		}
		
		return join;
	}
	
	public static CubeXType makeSubstitution(CubeXType type,TypeVarSubstitution sub) throws TypeCheckException
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

		if(type.isClass())
		{

			CubeXTypeClass baseClass = (CubeXTypeClass)type;

			ArrayList<CubeXType> newParameters =new ArrayList<CubeXType>();
			for(int i=0; i<baseClass.parameters.size(); i++)
			{
				newParameters.add(makeSubstitution(baseClass.parameters.get(i), sub));
			}
			return CubeXTypeClass.NewCubeXTypeClass(baseClass.name, newParameters);
		}
		
		if(type.isInterface())
		{

			CubeXTypeInterface baseInterface = (CubeXTypeInterface)type;

			ArrayList<CubeXType> newParameters =new ArrayList<CubeXType>();
			for(int i=0; i<baseInterface.parameters.size(); i++)
			{
				newParameters.add(makeSubstitution(baseInterface.parameters.get(i), sub));
			}
			return new CubeXTypeInterface(baseInterface.name, newParameters);
		}
		
		
		return type;
	}

	
	public static CubeXType validateType(CubeXType type, boolean isInExtends, ClassContext classCon, TypeVariableContext typeVarCon) throws TypeCheckException, ContextException 
	{
		if(type.isNothing() && isInExtends)
			throw new TypeCheckException();
		
		if(type.isThing())
			return type;
		
		if(!type.isExtendable() && isInExtends)
			throw new TypeCheckException("Trying to extend inextendable.");
		
		if(type.isClass()||type.isInterface())
		{
			CubeXClassBase base = classCon.lookup(((CubeXTypeClassBase)type).name);
			if(base==null)
				throw new ContextException("Bad base class");
			
			CubeXTypeClassBase typeBase =(CubeXTypeClassBase)type;
			
			if(typeBase.isClass())
				typeBase=((CubeXTypeClass)typeBase).getActualType(classCon);
			
			ArrayList<CubeXType> newParameters = new ArrayList<CubeXType>();
			for(CubeXType t : typeBase.parameters)
			{
				newParameters.add(validateType(t, false, classCon, typeVarCon));
			}
			typeBase.parameters=newParameters;
			return typeBase;
		}

		if(type.isIntersection())
		{
			CubeXTypeIntersection intersection = (CubeXTypeIntersection)type;
			
			intersection.left=validateType(intersection.left, isInExtends, classCon, typeVarCon);
			intersection.right=validateType(intersection.right, isInExtends, classCon, typeVarCon);
			
			if(!intersection.right.getConstructableComponent().isThing())
				throw new TypeCheckException("Class on the right side of the intersection");
			
			for(CubeXType superTypeLeft : getSuperTypes(intersection.left, classCon))
			{
				for(CubeXType superTypeRight : getSuperTypes(intersection.right, classCon))
				{
					if(superTypeLeft.equals(superTypeRight))
						throw new TypeCheckException("Common super parent");
				}
			}
			
			ArrayList<CubeXFunction> leftFuns = intersection.left.getAllFunctions(classCon);
			ArrayList<CubeXFunction> rightFuns = intersection.right.getAllFunctions(classCon);

			for(CubeXFunction leftFun : leftFuns)
			{
				for(CubeXFunction rightFun : rightFuns)
				{
					if(leftFun.getName().equals(rightFun.getName()))
						throw new TypeCheckException();
				}
			}
		}
		return type;
	}
	
	protected abstract ArrayList<CubeXFunction> getAllFunctions(ClassContext classCon) throws ContextException;

	/**
	 * Checks if child is subtype of parent
	 * @param child
	 * @param parent
	 * @return True if it is.
	 * @throws ContextException 
	 */
	public static boolean isSubType(CubeXType child, CubeXType parent, ClassContext classCon) throws ContextException
	{
		if(child.isNothing())
			return true;
		if(child.isThing())
			return parent.isThing();
		if(parent.isNothing())
			return false;
		if(parent.isThing())
			return true;
		
		if(child.isIntersection())
		{
			CubeXTypeIntersection intersection1 = (CubeXTypeIntersection)child;
			return isSubType(intersection1.left, parent, classCon) || isSubType(intersection1.right, parent, classCon);
		}
		
		if(parent.isIntersection())
		{
			CubeXTypeIntersection intersection2 = (CubeXTypeIntersection)parent;
			return isSubType(child, intersection2.left, classCon) && isSubType(child, intersection2.right, classCon);
		}
		
		if(child.isIterable())
		{
			if(parent.isIterable())
			{
				CubeXTypeIterable iterable1 = (CubeXTypeIterable)child;
				CubeXTypeIterable iterable2 = (CubeXTypeIterable)parent;
				return isSubType(iterable1.getInnerType(), iterable2.getInnerType(), classCon);
			}
			
			return false;
		}
		if(parent.isIterable())
			return false;
	
		if(child.isVariable())
		{
			if(parent.isVariable())
			{
				CubeXTypeVariable var1 = (CubeXTypeVariable)child;
				CubeXTypeVariable var2 = (CubeXTypeVariable)parent;
				return var1.equals(var2);
			}
			return false;
		}
		if(parent.isVariable())
			return false;
		
		CubeXTypeClassBase base1 = (CubeXTypeClassBase)child;
		CubeXTypeClassBase base2 = (CubeXTypeClassBase)parent;
		
		for(CubeXType base1Type : getSuperTypes(base1, classCon))
		{
			if(base1Type.equals(base2))
				return true;
		}
		
		return false;
	}


	public static ArrayList<CubeXType> getSuperTypes(CubeXType type, ClassContext classCon) throws ContextException {
		
		ArrayList<CubeXType> supers =new ArrayList<CubeXType>();
	
		if(type.isVariable())
		{
			supers.add(type);
		}
		if(type.isClass()||type.isInterface())
		{
			supers.add(type);
			supers.addAll(getSuperTypes(((CubeXTypeClassBase)type).getDeclaration(classCon).getParentType(),classCon));
		}
		else if(type.isIntersection())
		{
			supers.addAll(getSuperTypes(((CubeXTypeIntersection)type).left,classCon));
			supers.addAll(getSuperTypes(((CubeXTypeIntersection)type).right,classCon));
		}
		return supers;
	}
	
}

