package main.program;
import java.util.ArrayList;
import java.util.Iterator;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXExpression;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeClass;
import main.type.CubeXTypeClassBase;
import main.type.CubeXTypeVariable;
import main.util.Tuple;
import main.util.TypeVarSubstitution;


public class CubeXClass extends CubeXClassBase {


	private ArrayList<CubeXArgument> constructorArgs;
	private ArrayList<CubeXStatement> statements;
	private ArrayList<CubeXExpression> superArgs;
	

	private FunctionContext myFunctionContext;
	
	public FunctionContext getFunctionContext()
	{
		return myFunctionContext;
	}
	
	public boolean isClass()
	{
		return true;
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
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException 
	{
		if (classCon.lookup(name)!=null)
			throw new ContextException();
	
		TypeVariableContext classTypeVarCon = (TypeVariableContext)typeVarCon.createChildContext();
		
		for(CubeXTypeVariable tvar : types)
		{
			classTypeVarCon.add(tvar.getName(), tvar);
		}
		
		parentType=CubeXType.validateType(parentType, true, classCon, typeVarCon);

		classCon.add(name, this);
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
		
		
		ArrayList<CubeXArgument> newArgs = new ArrayList<CubeXArgument>();
		for(CubeXArgument arg : constructorArgs)
		{
			
			
			CubeXType newType = CubeXType.validateType(arg.type, false,  classCon, classTypeVarCon);
			newArgs.add(new CubeXArgument(arg.variable, newType));
			newVarCon.add(arg.variable.getName(), newType);
		}
		constructorArgs=newArgs;
		
		for(CubeXStatement stat : statements)
		{
			stat.typecheck(classCon, funCon, newVarCon, classTypeVarCon);
		}
		
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
				CubeXType tpe = thatArgIt.next().type;
				
				if(!CubeXType.isSubType(exp.getType(classCon, funCon, varCon, classTypeVarCon), tpe, classCon))
					throw new TypeCheckException();
			}
			
		}
		
		FunctionContext innerFunCon = (FunctionContext)funCon.createChildContext();
		
		for(CubeXFunction f : functions)
		{
			if(f.isDeclaration())
			{
				Tuple<TypeVarSubstitution, CubeXFunction> methodTuple=parentType.methodLookup(f.getName(), classCon);
				if(methodTuple.second==null)
					throw new TypeCheckException();
				f=methodTuple.second;
			}
			else
			{
				CubeXFunction g = funCon.lookup(f.getName());
				if(g!=null)
					throw new TypeCheckException();
				
			}
			innerFunCon.add(f.getName(), f);
			
		}
		
		ArrayList<CubeXType> parents = CubeXType.getSuperTypes(parentType,classCon);
		ArrayList<CubeXFunction> noChecking = new ArrayList<CubeXFunction>();
		for(CubeXType pp : parents)
		{
			if(pp.isVariable())
				throw new TypeCheckException();
			CubeXTypeClassBase p = (CubeXTypeClassBase)pp;
			
			for(CubeXFunction pFun : p.getDeclaration(classCon).functions)
			{
				CubeXFunction testFun = innerFunCon.lookup(pFun.getName());
				if(testFun==null)
				{
					if(pFun.isDeclaration())
						throw new TypeCheckException();
					noChecking.add(pFun);
					innerFunCon.add(pFun.getName(), pFun);
				}
			}
			
		}
		this.myFunctionContext=innerFunCon;
		for(CubeXFunction f : functions)
		{
			if(!f.isDeclaration())
				f.typecheck(classCon, innerFunCon, newVarCon, classTypeVarCon);
		}
		
		functions.addAll(noChecking);
		
		
		
		return null;
	}
}
