package main.program;
import java.util.ArrayList;
import java.util.HashSet;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.type.CubeXTypeClassBase;
import main.type.CubeXTypeVariable;
import main.util.CubeXArgument;
import main.util.Tuple;


public class CubeXInterface extends CubeXClassBase {

	
	public CubeXInterface(String name, ArrayList<CubeXTypeVariable> types, CubeXType parentType, ArrayList<CubeXFunction> functions)
	{
		super(name, types,parentType,functions);
	}

	
	public boolean isInterface()
	{
		return true;
	}


	@Override
	public Tuple<Boolean, CubeXType> typecheck(boolean force, ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException,TypeCheckException 
	{
		if (classCon.lookup(name)!=null)
			throw new ContextException("Interface extends constructable");
	
		TypeVariableContext classTypeVarCon = (TypeVariableContext)typeVarCon.createChildContext();
		parentType = CubeXType.validateType(parentType, true, classCon, typeVarCon);

		if(!parentType.getConstructableComponent().isThing())
			throw new TypeCheckException("Interface extending class");
		
		for(CubeXTypeVariable tvar : types)
		{
			classTypeVarCon.add(tvar.getName(), tvar);
		}
		
		classCon.add(name, this);
		VariableContext newVarCon = (VariableContext)varCon.createChildContext();
			
		FunctionContext innerFunCon = (FunctionContext)funCon.createChildContext();
		
		for(CubeXFunction f : functions)
		{
			CubeXFunction g = funCon.lookup(f.getName());
			if(g!=null)
				throw new TypeCheckException();
			f.setParent(this);
			innerFunCon.add(f.getName(), f);
			
		}
		
		ArrayList<CubeXType> parents = CubeXType.getSuperTypes(parentType,classCon);
		ArrayList<CubeXFunction> noChecking = new ArrayList<CubeXFunction>();
		for(CubeXType pp : parents)
		{
			if(pp.isVariable())
				throw new TypeCheckException("Parent is variable");
			CubeXTypeClassBase p = (CubeXTypeClassBase)pp;
			
			p.getTypeVarSub(classCon);
			
			for(CubeXFunction pFun : p.getDeclaration(classCon).functions)
			{
				CubeXFunction testFun = innerFunCon.lookup(pFun.getName());
				if(testFun==null)
				{
					noChecking.add(pFun);
					innerFunCon.add(pFun.getName(), pFun);
				}
			}
			
		}
		this.myFunctionContext=innerFunCon;
		
		for(CubeXFunction f : functions)
		{
			if(!f.isDeclaration())
			{
				f.typecheck(force, classCon, innerFunCon, newVarCon, classTypeVarCon, setField, this);
			}
			else
			{
				ArrayList<CubeXArgument> newArgs = new ArrayList<CubeXArgument>();
				for(CubeXArgument arg : f.getArglist())
				{
					
					
					CubeXType newType = CubeXType.validateType(arg.type, false,  classCon, classTypeVarCon);
					newArgs.add(new CubeXArgument(arg.variable, newType));
					newVarCon.add(arg.variable.getName(), newType);
				}
				f.setArglist(newArgs);
				f.setReturnType(CubeXType.validateType(f.getReturnType(), false, classCon, classTypeVarCon));
			}
		}
		

		
		return null;
	}


	@Override
	public String preC() {
		// TODO Auto-generated method stub
		return "";
	}


	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		
		for(CubeXFunction fun : functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name.equals(this.getName()))
			{
				sb.append(fun.preC());
				sb.append(fun.toC());
			}
		}
		
		return sb.toString();
	}


	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("interface ").append(name).append(" < ");
		String prefix ="";
		for(CubeXTypeVariable t : types)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> extends ").append(parentType.toString()).append(" {");
		
		for(CubeXFunction a : functions)
		{
			sb.append(" ").append(a.toString());
		}	
		sb.append(" }");
		return sb.toString();
	}


	@Override
	public ArrayList<CubeXProgramPiece> initializeSucc(CubeXProgramPiece after, boolean isTopLevel) {
	
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.initializeSucc(null, false);
			}
		}
		
		
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();
		addSucc(after, isTopLevel);
		return returns;
	}



	@Override
	public void initializeUsedVariables(boolean globals, HashSet<CubeXFunction> ignoredFunctions)
	{
		return;
	}


	@Override
	public void updateDeadVariables() {
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.updateDeadVariables();
			}
		}
		
	}
	
	@Override
	public CubeXProgramPiece flatten() {
		flattenFunctions();
		
		return this;
	}


	@Override
	public void addBoxes() 
	{
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.addBoxes();
			}
		}
	}


	@Override
	public void simplifyFunctionBoxes() {
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.simplifyFunctionBoxes();
			}
		}
		
	}
	
	@Override
	public void primitivifyVariables() {
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.primitivifyVariables();
			}
		}
		
	}
	
	@Override
	public void reduceBoxes() {
		for(CubeXFunction fun : this.functions)
		{
			if(!fun.isDeclaration() && fun.getParent().name==this.name)
			{
				fun.reduceBoxes();
			}
		}
		
	}
}
