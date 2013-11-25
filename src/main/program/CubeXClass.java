package main.program;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import main.Optimizations.ExpressionContext;
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
			definedFields.add(arg.variable.getName());
			arg.variable.trySetField(true, this);
			newVarCon.add(arg.variable.getName(), newType);
		}
		constructorArgs=newArgs;
		
		for(CubeXStatement stat : statements)
		{
			stat.typecheck(force, classCon, funCon, newVarCon, classTypeVarCon, true, this);
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
	public String toC() 
	{
		StringBuilder sb = new StringBuilder();
		
		for(CubeXFunction fun : functions)
		{
			if(fun.getParent().name.equals(this.getName()))
			{
				sb.append(fun.preC());
				sb.append(fun.toC());
			}
		}
		
		return sb.toString();
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
	
	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel) {
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		if(statements.size()!=0)
		{			
			CubeXStatement cur = statements.get(0);

			for(int i=0; i<statements.size()-1; ++i)
			{
				CubeXStatement next = statements.get(i+1);
				returns.addAll(cur.initializeSucc(next,false));
				cur=next;
			}
			returns.addAll(cur.initializeSucc(null,false));
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				returns.addAll(fun.initializeSucc(null, false));
			}
		}
		
		addSucc(after, isTopLevel);
		return returns;
	}


	
	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		for(CubeXStatement stat : statements)
		{
			stat.getUsedVariables(globals, ignoredFunctions);
		}
		
	}
	HashSet<String> innerGlobals = new HashSet<String>();
	public HashSet<String> getInnerGlobals(HashSet<CubeXFunction> ignoredFunctions)
	{
		for(CubeXStatement stat : statements)
		{
			innerGlobals.addAll(stat.getUsedVariables(true, ignoredFunctions));
		}
		return innerGlobals;
	}
	
	public CubeXStatement getLastStatement()
	{
		if(statements.isEmpty())
			return null;
		CubeXStatement last = statements.get(statements.size()-1);
		
		for(CubeXExpression arg : superArgs)
		{
			last.currentOut.addAll(arg.getUsedVars(false, new HashSet<CubeXFunction>()));
		}

		return last;
	}

	@Override
	public void updateDeadVariables()
	{
		for(CubeXStatement stat : statements)
		{
			stat.updateDeadVariables();
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				fun.updateDeadVariables();
			}
		}
	}

	@Override
	public CubeXProgramPiece flatten() {
		ArrayList<CubeXStatement> flattenedStatements = new ArrayList<CubeXStatement>();
		for(CubeXStatement stat : statements){
			CubeXStatement flattened = (CubeXStatement)stat.flatten();
			if(flattened.isStatement() && flattened.isBlock()){
				flattenedStatements.addAll(((CubeXBlock)flattened).getInnerStatements());
			}
			else{
				flattenedStatements.add(flattened);
			}
		}
		statements = flattenedStatements;
		
		flattenFunctions();
		
		return this;
	}

	@Override
	public void addBoxes() {
		
		for(CubeXStatement stat : statements)
		{
			stat.addBoxes();
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				fun.addBoxes();
			}
		}	
		for(int i=0; i<superArgs.size(); ++i)
		{
			CubeXExpression newEntry = superArgs.get(i).addBoxes();
			if(newEntry==superArgs.get(i))
				continue;
			superArgs.set(i, newEntry);
		}
		
	}
	
	public void simplifyFunctionBoxes() {
		
		for(CubeXStatement stat : statements)
		{
			stat.simplifyFunctionBoxes();
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				fun.simplifyFunctionBoxes();
			}
		}
		
		for(int i=0; i<superArgs.size(); ++i)
		{
			CubeXExpression newEntry = superArgs.get(i).simplifyFunctionBoxes();
			if(newEntry==superArgs.get(i))
				continue;
			superArgs.set(i, newEntry);
		}
	}
	
	public void primitivifyVariables() {
		
		for(CubeXStatement stat : statements)
		{
			stat.primitivifyVariables();
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				fun.primitivifyVariables();
			}
		}
		
		for(int i=0; i<superArgs.size(); ++i)
		{
			CubeXExpression newEntry = superArgs.get(i).primitivifyVariables();
			if(newEntry==superArgs.get(i))
				continue;
			superArgs.set(i, newEntry);
		}
	}
	
	@Override
	public void reduceBoxes() {
		
		for(CubeXStatement stat : statements)
		{
			stat.reduceBoxes();
		}
		
		for(CubeXFunction fun : this.functions)
		{
			if(fun.getParent().name==this.name)
			{
				fun.reduceBoxes();
			}
		}	
		for(int i=0; i<superArgs.size(); ++i)
		{
			CubeXExpression newEntry = superArgs.get(i).reduceBoxes();
			if(newEntry==superArgs.get(i))
				continue;
			superArgs.set(i, newEntry);
		}
		
	}

	@Override
	public ExpressionContext eliminateCommonSubexpressions(ExpressionContext con) throws ContextException {
		ExpressionContext localCon = con.createChildContext();
		
		for(CubeXStatement stat : statements){
			localCon = stat.eliminateCommonSubexpressions(localCon);
		}
		
		for(CubeXFunction fun : functions){
			fun.eliminateCommonSubexpressions(localCon);
		}
		
		return localCon;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXClass))
			return false;
		
		CubeXClass oC = (CubeXClass) other;
		
		if(types.size() != oC.types.size())
			return false;
		
		if(functions.size() != oC.functions.size())
			return false;
		
		if(constructorArgs.size() != oC.constructorArgs.size())
			return false;
		
		if(statements.size() != oC.statements.size())
			return false;
		
		if(superArgs.size() != oC.superArgs.size())
			return false;
		
		for(int i = 0; i < types.size(); i++)
			if(!(types.get(i).equals(oC.types.get(i))))
				return false;

		for(int i = 0; i < functions.size(); i++)
			if(!(functions.get(i).equals(oC.functions.get(i))))
				return false;
		
		for(int i = 0; i < constructorArgs.size(); i++)
			if(!(constructorArgs.get(i).equals(oC.constructorArgs.get(i))))
				return false;
		
		for(int i = 0; i < statements.size(); i++)
			if(!(statements.get(i).equals(oC.statements.get(i))))
				return false;
		
		for(int i = 0; i < superArgs.size(); i++)
			if(!(superArgs.get(i).equals(oC.superArgs.get(i))))
				return false;
		
		return name.equals(oC.name) && parentType.equals(oC.parentType);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		for(int i = 0; i < types.size(); i++)
			result = prime * result + types.get(i).hashCode();

		for(int i = 0; i < functions.size(); i++)
			result = prime * result + functions.get(i).hashCode();
		
		for(int i = 0; i < constructorArgs.size(); i++)
			result = prime * result + constructorArgs.get(i).hashCode();
		
		for(int i = 0; i < statements.size(); i++)
			result = prime * result + statements.get(i).hashCode();
		
		for(int i = 0; i < superArgs.size(); i++)
			result = prime * result + superArgs.get(i).hashCode();
		
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentType == null) ? 0 : parentType.hashCode());
		return result;
	}
}
