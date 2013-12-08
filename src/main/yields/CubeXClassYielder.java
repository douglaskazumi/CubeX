package main.yields;

import java.util.ArrayList;
import java.util.Iterator;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeClass;
import main.type.CubeXTypeClassBase;
import main.type.CubeXTypeIterable;
import main.type.CubeXTypeVariable;
import main.util.CubeXArgument;
import main.util.Triple;
import main.util.Tuple;
import main.util.TypeVarSubstitution;

public class CubeXClassYielder extends CubeXClass {

	private CubeXYielder yielder;
	
	public CubeXClassYielder(String name, ArrayList<CubeXTypeVariable> types, ArrayList<CubeXArgument> constructorArgs, ArrayList<CubeXType> iterableTypeList, ArrayList<CubeXStatement> stats, ArrayList<CubeXExpression> superArgs, CubeXYielder yielder, ArrayList<CubeXFunction> funs)
	{
		super(name, types, constructorArgs, new CubeXTypeIterable(iterableTypeList), stats, superArgs, funs);
		
		this.yielder=yielder;
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par, boolean isYielder) throws ContextException,TypeCheckException 
	{
		if (classCon.lookup(name)!=null)
			throw new ContextException("Yielder Class already in context");
	
		TypeVariableContext classTypeVarCon = (TypeVariableContext)typeVarCon.createChildContext();
		
		for(CubeXTypeVariable tvar : types)
		{
			classTypeVarCon.add(tvar.getName(), tvar);
		}
		
		//CHECK THIS
		parentType=CubeXType.validateType(parentType, true, classCon, classTypeVarCon);
		if(!parentType.isIterable() || parentType.isYielder())
			throw new TypeCheckException("Not a valid Yielder parent");
		classCon.add(name, this);
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
		
		
		ArrayList<CubeXArgument> newArgs = new ArrayList<CubeXArgument>();
		for(CubeXArgument arg : constructorArgs)
		{
			CubeXType newType = CubeXType.validateType(arg.type, false,  classCon, classTypeVarCon);
			newArgs.add(new CubeXArgument(arg.variable, newType));
			definedFields.add(arg.variable.getName());
			arg.variable.trySetField(true, this);
			newVarCon.add(arg.variable.getName(), newType);
		}
		constructorArgs=newArgs;
		
		for(CubeXStatement stat : statements)
		{
			stat.typecheck(force, classCon, funCon, newVarCon, classTypeVarCon, true, this, false);
		}
		
		ArrayList<String> parentFields = new ArrayList<>();
		CubeXType pConstructor = parentType.getConstructableComponent();
		if(pConstructor==CubeXType.getThing())
		{
			if(superArgs.size()!=0)
				throw new TypeCheckException();
		}
		else
		{
			CubeXTypeClass pClass = (CubeXTypeClass)pConstructor;
			
			ArrayList<CubeXArgument> expectedSuperArgs = pClass.getDeclaration(classCon).getConstructorArgs();
			
			Iterator<? extends CubeXExpression> thisArgIt = superArgs.iterator();
			Iterator<? extends CubeXArgument> thatArgIt = expectedSuperArgs.iterator();
			while(thisArgIt.hasNext())
			{
				CubeXExpression exp = thisArgIt.next();			
				TypeVarSubstitution clSub = ((CubeXTypeClass)this.getParentType().getConstructableComponent()).getTypeVarSub(classCon);
				CubeXType tpe = CubeXType.makeSubstitution(thatArgIt.next().type, clSub);
				
				if(!CubeXType.isSubType(exp.getType(force, classCon, funCon, newVarCon, classTypeVarCon, true, this), tpe, classCon))
					throw new TypeCheckException();
			}
			
			parentFields.addAll(pClass.getDeclaration(classCon).definedFields);
		}
		definedFields.clear();
		definedFields.addAll(parentFields);
		for (String varName : newVarCon.getInnerMap().keySet())
		{
			definedFields.add(varName);
		}
			
		FunctionContext innerFunCon = (FunctionContext)funCon.createChildContext();
		
		for(CubeXFunction f : functions)
		{
			if(f.isDeclaration())
			{
				Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> methodTuple=parentType.methodLookup(f.getName(), classCon);
				if(methodTuple.second==null)
					throw new TypeCheckException("Function not found in parent");
				f=methodTuple.second;
			}
			else
			{
				CubeXFunction g = funCon.lookup(f.getName());
				if(g!=null)
					throw new TypeCheckException("Function not found in global");
				f.setParent(this);
				
				try
				{
					if(!parentType.isThing() && !parentType.isVariable())
					{
					
						Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> methodTuple=parentType.methodLookup(f.getName(), classCon);
						CubeXFunction overLoadedFunction = methodTuple.second;
						TypeVarSubstitution cbSub = null;

						ArrayList<CubeXType> parentParents = CubeXType.getSuperTypes(parentType, classCon);
						CubeXClassBase funParent = overLoadedFunction.getParent();
						for(CubeXType p : parentParents)
						{
							if(((CubeXTypeClassBase)p).getName().equals(funParent.getName()))	
							{
								cbSub=((CubeXTypeClassBase)p).getTypeVarSub(classCon);
								break;
							}
						}
						
						CubeXType substitutedType = CubeXType.makeSubstitution(overLoadedFunction.getReturnType(), cbSub);
						if(!CubeXType.isSubType(substitutedType, f.getReturnType(), classCon) && !CubeXType.isSubType(f.getReturnType(),substitutedType, classCon))
							throw new TypeCheckException("Overloading function");
						
						if(overLoadedFunction.getArglist().size()!=f.getArglist().size())
							throw new TypeCheckException("Overloading function");
						
						for(int i=0; i<overLoadedFunction.getArglist().size(); i++)
						{
							CubeXType overArgType = overLoadedFunction.getArglist().get(i).type;
							CubeXType argType = f.getArglist().get(i).type;
							substitutedType = CubeXType.makeSubstitution(overArgType, cbSub);
							if(!CubeXType.isSubType(argType, substitutedType, classCon) && !CubeXType.isSubType(substitutedType, argType, classCon))
								throw new TypeCheckException("Overloading function");
							
						}
					}
				}
				catch(ContextException e)
				{					
				}
				
			}
			innerFunCon.add(f.getName(), f);
			
		}
		
		ArrayList<CubeXType> parents = CubeXType.getSuperTypes(parentType,classCon);
		ArrayList<CubeXFunction> noChecking = new ArrayList<CubeXFunction>();
		for(CubeXType pp : parents)
		{
			if(pp.isVariable())
				throw new TypeCheckException("Parent is variable");
			CubeXTypeClassBase p = (CubeXTypeClassBase)pp;
			
			for(CubeXFunction pFun : p.getDeclaration(classCon).functions)
			{
				CubeXFunction testFun = innerFunCon.lookup(pFun.getName());
				if(testFun==null)
				{
					if(pFun.isDeclaration())
						throw new TypeCheckException("Parent function is declaration");
					noChecking.add(pFun);
					innerFunCon.add(pFun.getName(), pFun);
				}
			}
			
		}
		this.myFunctionContext=innerFunCon;
		for(CubeXFunction f : functions)
		{
			if(!f.isDeclaration())
				f.typecheck(force, classCon, innerFunCon, newVarCon, classTypeVarCon, false, this, false);
		}
		
		functions.addAll(noChecking);
		
		CubeXType yieldType = ((CubeXTypeIterable)parentType).getInnerType();
		TypeVarSubstitution clSub = ((CubeXTypeClass)this.getParentType().getConstructableComponent()).getTypeVarSub(classCon);
		Tuple<Boolean, CubeXType> returnedValue = yielder.typecheck(force, classCon, innerFunCon, newVarCon, classTypeVarCon, false, this, true);
		if(!returnedValue.first || !CubeXType.isSubType(returnedValue.second, CubeXType.makeSubstitution(yieldType, clSub), classCon))
			throw new TypeCheckException("Bad yield return Value");
		
		
		return null;
	}
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("class ").append(name).append(" < ");
		String prefix ="";
		for(CubeXTypeVariable t : types)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		prefix="";
		for(CubeXArgument a : constructorArgs)
		{
			sb.append(prefix).append(a.toString()).append(" ");
			prefix=", ";
		}
		sb.append(") extends ").append(parentType.toString()).append(" { ");
		
		for(CubeXStatement stat : statements)
		{
			sb.append(stat).append(" ");
		}
		sb.append("super ( ");
		prefix="";
		for(CubeXExpression a : superArgs)
		{
			sb.append(prefix).append(a.toString());
			prefix=", ";
		}
		sb.append(") ;");
		sb.append(" ").append(yielder.toString()).append("");
		for(CubeXFunction a : functions)
		{
			sb.append(" ").append(a.toString());
		}	
		
		sb.append(" }");
		return sb.toString();
	}
}
