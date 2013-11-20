package main.expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import main.Optimizations.Boxer;
import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.*;
import main.exceptions.*;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXAssignment;
import main.statement.CubeXBlock;
import main.statement.CubeXStatement;
import main.type.*;
import main.util.*;
import main.program.*;

public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	
	private boolean simplified = false;
	
	public CubeXExpression getParent() {
		return parent;
	}

	private String name;
	private ArrayList<? extends CubeXType> parameters;
	private ArrayList<CubeXExpression> args;
	public ArrayList<CubeXExpression> getArgs() {
		return args;
	}

	private String tempVar=null;
	
	private enum CallType {GLOBAL, CONSTRUCTOR, FUNCTION}
	
	private CallType calltype;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<? extends CubeXType> parameters, ArrayList<CubeXExpression> args)
	{
		this.parent=parent;
		this.name=name;
		if(parameters==null)
		{
			parameters = new ArrayList<CubeXType>();
		}
		this.parameters=parameters;
		if(args==null || (args.size()>0 && args.get(0)==null))
		{
			args = new ArrayList<CubeXExpression>();
		}
		this.args=args;
	}
	
	public CubeXFunctionCall(CubeXExpression parent, String name, CubeXExpression arg)
	{
		this(parent, name, null, new ArrayList<CubeXExpression>(Arrays.asList(arg)));
	}
	
	public static CubeXFunctionCall copy(CubeXFunctionCall original)
	{
		return new CubeXFunctionCall(original.parent, original.name, original.parameters, original.args);
	}
	
	public static CubeXFunctionCall copy(CubeXFunctionCall original,CubeXExpression newParent)
	{
		return new CubeXFunctionCall(newParent, original.name, original.parameters, original.args);
	}
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException 
	{
		CubeXFunction fun;
	
		//Object function calls
		if(parent!=null)
		{
			calltype=CallType.FUNCTION;
			CubeXType pType = parent.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
			if(pType.isVariable())
				throw new TypeCheckException("Parent type is variable");
			
			Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res = pType.methodLookup(name, classCon);
			fun = res.second;
			if(fun.getTypes().size()!=this.parameters.size())
				throw new TypeCheckException("Bad number of parameters to function: " + name + ". Expected: " + fun.getTypes().size() + ". Given: " + this.parameters.size());
			if(fun.getArglist().size()!=this.args.size())
				throw new TypeCheckException("Bad number of arguments to function: " + name + ". Expected: " + fun.getArglist().size() + ". Given: " + this.args.size());
			
			TypeVarSubstitution funSub = new TypeVarSubstitution(fun.getTypes(), parameters);
			ArrayList<CubeXType> parentParents = CubeXType.getSuperTypes(pType, classCon);
			CubeXClassBase funParent = fun.getParent();
			TypeVarSubstitution classSub = res.first;
			for(CubeXType p : parentParents)
			{
				
				if(((CubeXTypeClassBase)p).getName().equals(funParent.getName()))
				{
					classSub=((CubeXTypeClassBase)p).getTypeVarSub(classCon);
					break;
				}
			}
			
			Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
			Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
			while(argValuesIt.hasNext())
			{
				CubeXExpression exp = argValuesIt.next();
				CubeXType tpe = CubeXType.makeSubstitution(CubeXType.makeSubstitution(argExpectedTypesIt.next().type, funSub), classSub);
				
				if(!CubeXType.isSubType(exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), tpe, classCon))
					throw new TypeCheckException("BAD ARGUMENT TO FUNCTION CALL");
			}
			
			return CubeXType.makeSubstitution(CubeXType.makeSubstitution(fun.getReturnType(), classSub), funSub);
		}
		else
		{
			//Global function call
			if(name.substring(0, 1).toLowerCase().equals(name.substring(0, 1)))
			{
				calltype=CallType.GLOBAL;
				fun=funCon.lookup(name);
				if(fun == null){
					throw new ContextException();
				}

				if(fun.getTypes().size()!=this.parameters.size())
					throw new TypeCheckException("Bad number of parameters to global function: " + name + ". Expected: " + fun.getTypes().size() + ". Given: " + this.parameters.size());
				if(fun.getArglist().size()!=this.args.size())
					throw new TypeCheckException("Bad number of arguments to global function: " + name + ". Expected: " + fun.getArglist().size() + ". Given: " + this.args.size());
				
				TypeVarSubstitution sub = new TypeVarSubstitution(fun.getTypes(), parameters);
				TypeVarSubstitution cbSub = null;
				
				if(fun.getParent()!=null)
				{
					ArrayList<CubeXType> parentParents = CubeXType.getSuperTypes(par.getParentType(), classCon);
					CubeXClassBase funParent = fun.getParent();
					for(CubeXType p : parentParents)
					{
						if(((CubeXTypeClassBase)p).getName().equals(funParent.getName()))	
						{
							cbSub=((CubeXTypeClassBase)p).getTypeVarSub(classCon);
							break;
						}
					}
				}
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub), cbSub);
										
					if(!CubeXType.isSubType(exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), tpe, classCon))
						throw new TypeCheckException("BAD ARGUMENT TO GLOBAL FUNCTION CALL");
				}
				
				return CubeXType.makeSubstitution(CubeXType.makeSubstitution(fun.getReturnType(), sub), cbSub);
			}
			else
			//Constructor
			{
				calltype=CallType.CONSTRUCTOR;
				CubeXClassBase base = classCon.lookup(name);
				if(base==null)
					throw new ContextException();
				
				
				if(base.isInterface())
					throw new TypeCheckException();
				
				CubeXClass clss = (CubeXClass)base;
				
				if(clss.getTypes().size()!=this.parameters.size())
					throw new TypeCheckException("Bad number of parameters to constructor: " + name + ". Expected: " + clss.getTypes().size() + ". Given: " + this.parameters.size());
				if(clss.getConstructorArgs().size()!=this.args.size())
					throw new TypeCheckException("Bad number of arguments to constructor: " + name + ". Expected: " + clss.getConstructorArgs().size() + ". Given: " + this.args.size());
				
				TypeVarSubstitution sub = new TypeVarSubstitution(clss.getTypes(), parameters);
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = clss.getConstructorArgs().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub);
					
					if(!CubeXType.isSubType(exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), tpe, classCon))
						throw new TypeCheckException("BAD ARGUMENT TO CONSTRUCTOR CALL");
				}
				
				return CubeXTypeClass.NewCubeXTypeClass(name, parameters);
			}
		}
	}
	
	@Override
	public String preC(CubeXProgramPiece par) {
		
		StringBuilder sb = new StringBuilder();
		
		if(parent!=null) //e.fun();
		{
			 sb.append(parent.preC(par));
			 tempVar = CUtils.getTempName();
			 if(par!=null)
			 {
				 par.addLocal(tempVar);
			 }
			 else
			 {
				 GlobalAwareness.addLocal(tempVar);
			 }
			 sb.append(CUtils.canonName(tempVar)).append(" = gc_inc(").append(parent.toC(par)).append(");\n");
		}
		
		for(CubeXExpression exp : args)
		{
			sb.append(exp.preC(par));
		}
		
		return sb.toString();
	}

	@Override
	public String toC(CubeXProgramPiece par) {
		StringBuilder sb = new StringBuilder();
		try
		{
			if(calltype==CallType.FUNCTION) //e.fun();
			{
	
					Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res =  parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext);
					CubeXClassBase cb = (CubeXClassBase)res.third.getDeclaration(GlobalContexts.classContext);
					
					ArrayList<CubeXFunction> funs = cb.getFunctions();
					int fIndex=-1;
					for(int i=0; i<funs.size(); ++i)
					{
						CubeXFunction f = funs.get(i);
						
						//MIGHT NEED TO CHANGE THIS EVENTUALLY (CHECKING FUNCTION NAME VS FUNCTION SCHEME)
						
						if(f.getName().equals(name))
						{
							fIndex=i;
							break;
						}
					}
					
					if(fIndex==-1)
						throw new Exception("Bad translation");
					
					sb.append("(((object_t * (*)(object_t *");
					for(@SuppressWarnings("unused") CubeXExpression exp : args)
					{
						sb.append(", ").append("object_t *");
					}
					sb.append("))").append("(getMethod(").append(CUtils.canonName(tempVar)).append(", ").append(cb.getID()).append(", ").append(fIndex).append(")))");
					sb.append("(gc_inc(").append(CUtils.canonName(tempVar)).append(")");
					for(CubeXExpression exp : args)
					{
						sb.append(", gc_inc(").append(exp.toC(par)).append(")");
					}
					sb.append("))");
					
	
			}
			else if(calltype==CallType.GLOBAL) //Global Function
			{
				CubeXFunction fun =  GlobalContexts.functionContext.lookup(name);
				sb.append("(").append(CUtils.canonName(fun, false)).append("(");
				String prefix="";
				for(CubeXExpression exp : args)
				{
					sb.append(prefix).append("gc_inc(").append(exp.toC(par)).append(")");
					prefix=", ";
				}
				sb.append("))");
			}
			else //Constructor
			{
				sb.append("(c_").append(name).append("(");
				String prefix="";
				for(CubeXExpression exp : args)
				{
					sb.append(prefix).append("gc_inc(").append(exp.toC(par)).append(")");
					prefix=", ";
				}
				sb.append("))");
			}
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if(tempVar!=null)
			temps.add(tempVar);
		for(CubeXExpression expr : args)
		{
			temps.addAll(expr.temps);
		}
		
		return sb.toString();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if(parent!=null)
			sb.append(parent.toString()).append(" . ");
		
		sb.append(name).append(" < ");
		String prefix ="";
		for(CubeXType t : parameters)
		{
			sb.append(prefix).append(t.toString()).append(" ");
			prefix=", ";
		}
		sb.append("> ( ");
		
		prefix ="";
		for(CubeXExpression expr : args)
		{
			sb.append(prefix).append(expr.toString()).append(" ");
			prefix=", ";
		}
		sb.append(")");
		return sb.toString();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public HashSet<String> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) 
	{
		HashSet<String> vars = new HashSet<>();
		if(calltype==CallType.FUNCTION)
		{
			vars.addAll(parent.getUsedVars(globals, ignoredFunctions));
			CubeXFunction fun=null;
			try
			{
				fun =  parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext).second;
				if(!ignoredFunctions.contains(fun))
				{
					HashSet<CubeXFunction> ignoredFunctionsNew = (HashSet<CubeXFunction>) ignoredFunctions.clone();
					ignoredFunctionsNew.add(fun);
					vars.addAll(fun.getInnerGlobals(ignoredFunctionsNew));
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(calltype==CallType.GLOBAL)
		{
			CubeXFunction fun=GlobalContexts.functionContext.lookup(name);
			if(!ignoredFunctions.contains(fun))
			{
				HashSet<CubeXFunction> ignoredFunctionsNew = (HashSet<CubeXFunction>) ignoredFunctions.clone();
				ignoredFunctionsNew.add(fun);
				vars.addAll(fun.getInnerGlobals(ignoredFunctionsNew));
			}
		}
		else // constructor
		{
			CubeXClass base = (CubeXClass)GlobalContexts.classContext.lookup(name);
			vars.addAll(base.getInnerGlobals(ignoredFunctions));
		}
		
		for(CubeXExpression arg : args)
		{
			vars.addAll(arg.getUsedVars(globals, ignoredFunctions));
		}
		
		return vars;
	}

	@Override
	public boolean isFunctionCall() {
		return true;
	}
	
	public boolean isNested(){
		return parent.isFunctionCall();
	}

	public CubeXStatement flattenArgs() {
		ArrayList<CubeXExpression> flattenedArgs = new ArrayList<>();
		CubeXBlock flattened = new CubeXBlock();
		for(CubeXExpression arg : args){
			if(arg.isFunctionCall()){
				CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), arg);
				flattened.add(((CubeXFunctionCall)arg).flatten(tempVar));
				flattenedArgs.add(tempVar.getVariable());
			}
			else{
				flattenedArgs.add(arg);
			}
		}
		args = flattenedArgs;
		return flattened;
	}	
	
	private boolean isArgFun(){
		for(CubeXExpression arg : args){
			if(arg.isFunctionCall()){
				return true;
			}
		}
		return false;
	}
	
	public CubeXStatement flatten(CubeXAssignment tempVar){
		CubeXBlock flattened = new CubeXBlock();		
		
		if(parent.isFunctionCall()){
			CubeXFunctionCall par = (CubeXFunctionCall)parent;
			CubeXAssignment tempVar2 = new CubeXAssignment(GlobalAwareness.getTempName(), par);
			flattened.add(par.flatten(tempVar2));
			this.parent = tempVar2.getVariable();
		}
		
		if(isArgFun()){
			flattened.add(flattenArgs());
		}
		
		flattened.add(tempVar);
		return flattened;
	}

	@Override
	public CubeXExpression reduceBoxes() 
	{
		if(parent!=null)
			parent=parent.reduceBoxes();
		
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).reduceBoxes();
			if(newEntry==args.get(i))
				continue;
			args.set(i, newEntry);
		}
		return this;
	}
	
	
	@Override
	public CubeXExpression addBoxes() 
	{
		if(parent!=null)
			parent=parent.addBoxes();
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).addBoxes();
			args.set(i, newEntry);
		}
		return this;
	}
	
	public CubeXExpression simplifyFunctionBoxes()
	{
		if(parent!=null)
		{
			if(parent.getTypeUnsafe().isInt())
			{
				if(name.equals("negative"))
				{
					parent = Boxer.unboxify(parent);
					simplified=true;
				}
				else if(args.size()==2 && args.get(0).getTypeUnsafe().isInt()  && args.get(1).getTypeUnsafe().isBool() && name.equals("lessThan"))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					args.set(0, Boxer.unboxify(args.get(0)));
					args.set(1, Boxer.unboxify(args.get(1)));
				}
				else if(args.size()==1 && args.get(0).getTypeUnsafe().isInt() && (name.equals("times") || name.equals("plus") || name.equals("minus") || name.equals("equals")))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					args.set(0, Boxer.unboxify(args.get(0)));
				}
			}
			else if(parent.getTypeUnsafe().isBool())
			{
				if(name.equals("negate"))
				{
					parent = Boxer.unboxify(parent);
					simplified=true;
				}
				else if(args.size()==2 && args.get(0).getTypeUnsafe().isInt()  && args.get(1).getTypeUnsafe().isBool() && name.equals("lessThan"))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					args.set(0, Boxer.unboxify(args.get(0)));
					args.set(1, Boxer.unboxify(args.get(1)));
				} 
				else if(args.size()==1 && args.get(0).getTypeUnsafe().isBool() && (name.equals("and") || name.equals("or") || name.equals("lessThan") || name.equals("equals")))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					args.set(0, Boxer.unboxify(args.get(0)));
				}
			}
		}
		
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).simplifyFunctionBoxes();
			if(newEntry==args.get(i))
				continue;
			args.set(i, newEntry);
		}
		return Boxer.boxify(this);
	}

	@Override
	public CubeXExpression primitivifyVariables() {
		if(parent!=null)
			parent=parent.primitivifyVariables();
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).primitivifyVariables();
			args.set(i, newEntry);
		}
		return this;
	}
}
