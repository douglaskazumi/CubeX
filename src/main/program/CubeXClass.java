package main.program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import main.c.*;
import main.context.*;
import main.exceptions.*;
import main.expression.*;
import main.statement.*;
import main.type.*;
import main.util.*;

public class CubeXClass extends CubeXClassBase {


	private ArrayList<CubeXArgument> constructorArgs;
	private ArrayList<CubeXStatement> statements;
	private ArrayList<CubeXExpression> superArgs;
	
	private FunctionContext myFunctionContext;
	
	public ArrayList<String> definedFields;

	public CubeXClass(String name, ArrayList<CubeXTypeVariable> types, ArrayList<CubeXArgument> constructorArgs, CubeXType parentType, ArrayList<CubeXStatement> stats, ArrayList<CubeXExpression> superArgs, ArrayList<CubeXFunction> functions)
	{
		super(name, types, parentType, functions);
	
		if(superArgs==null)
			superArgs=new ArrayList<CubeXExpression>();
		if(constructorArgs==null)
			constructorArgs=new ArrayList<CubeXArgument>();
		if(stats==null)
			stats=new ArrayList<CubeXStatement>();		
		
		this.constructorArgs=constructorArgs;
		this.statements=stats;
		this.superArgs=superArgs;
		
		
		definedFields=new ArrayList<String>();
	}

	public boolean isClass()
	{
		return true;
	}

	public FunctionContext getFunctionContext()
	{
		return myFunctionContext;
	}
	
	public void setFunctionContextManual(ArrayList<CubeXFunction> funs, FunctionContext funCon) throws ContextException
	{
		FunctionContext innerCon = (FunctionContext) funCon.createChildContext();
		
		for(CubeXFunction f : funs)
		{
			innerCon.add(f.getName(), f);
		}
		
		myFunctionContext=innerCon;
	}
	
	
	
	public ArrayList<CubeXArgument> getConstructorArgs() {
		return constructorArgs;
	}

	public ArrayList<CubeXStatement> getStatements() {
		return statements;
	}

	public ArrayList<CubeXExpression> getSuperArgs() {
		return superArgs;
	}
	
	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		if (classCon.lookup(name)!=null)
			throw new ContextException("Class already in context");
	
		TypeVariableContext classTypeVarCon = (TypeVariableContext)typeVarCon.createChildContext();
		
		for(CubeXTypeVariable tvar : types)
		{
			classTypeVarCon.add(tvar.getName(), tvar);
		}
		
		parentType=CubeXType.validateType(parentType, true, classCon, classTypeVarCon);
		
		classCon.add(name, this);
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
		
		
		ArrayList<CubeXArgument> newArgs = new ArrayList<CubeXArgument>();
		for(CubeXArgument arg : constructorArgs)
		{
			CubeXType newType = CubeXType.validateType(arg.type, false,  classCon, classTypeVarCon);
			newArgs.add(new CubeXArgument(arg.variable, newType));
			arg.variable.trySetField(true, this);
			newVarCon.add(arg.variable.getName(), newType);
		}
		constructorArgs=newArgs;
		
		for(CubeXStatement stat : statements)
		{
			stat.typecheck(force, classCon, funCon, newVarCon, classTypeVarCon, true, this);
		}
		
		definedFields.clear();
		
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
			
			definedFields.addAll(pClass.getDeclaration(classCon).definedFields);
		}
		
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
				f.typecheck(force, classCon, innerFunCon, newVarCon, classTypeVarCon, false, this);
		}
		
		functions.addAll(noChecking);
		
		return null;
	}

	@Override
	public String preC() {
		return "";
	}

	@Override
	public String toC() {
		return "";
	}
	
	public VTable generateVTable() throws TypeCheckException
	{
		return VTable.getVTable(this);
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
		for(CubeXFunction a : functions)
		{
			sb.append(" ").append(a.toString());
		}	
		
		sb.append(" }");
		return sb.toString();
	}
}
