package main.expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import main.Optimizations.Boxer;
import main.c.CUtils;
import main.c.GlobalAwareness;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.GlobalContexts;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.program.CubeXYielder;
import main.statement.CubeXAssignment;
import main.statement.CubeXBlock;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeClass;
import main.type.CubeXTypeClassBase;
import main.type.CubeXTypeIterable;
import main.type.CubeXTypeYielder;
import main.util.CubeXArgument;
import main.util.Triple;
import main.util.TypeVarSubstitution;

public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	
	private CubeXType faketcb;
	
	private boolean simplified = false;

	private String name;
	private ArrayList<CubeXType> parameters;
	private ArrayList<CubeXExpression> args;

	private String tempVar=null;
	
	private enum CallType {GLOBAL, CONSTRUCTOR, FUNCTION}
	
	private CallType calltype;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<CubeXType> parameters, ArrayList<CubeXExpression> args)
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
	
	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXProgramPiece par) throws ContextException, TypeCheckException 
	{
		CubeXFunction fun;
	
		//Object function calls
		boolean isObjectCall = parent!=null || (par!=null && (par.isClass()||par.isInterface()) && (GlobalContexts.functionContext.lookup(name)==null) && funCon.lookup(name)!=null);
		
		if(isObjectCall)
		{
			
			calltype=CallType.FUNCTION;

			CubeXType pType;

			if(parent==null)
			{
				
				ArrayList<CubeXType> types = new ArrayList<>();
				types.addAll(((CubeXClassBase)par).getTypes());
					
				pType =  CubeXTypeClass.NewCubeXTypeClass(((CubeXClassBase)par).getName(), types);
			}
			else
			{
				pType = parent.getType(force, classCon, funCon, varCon, typeVarCon, setField, par);
			}
			faketcb = pType;
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
					if(!par.isFunction())
					{
					ArrayList<CubeXType> parentParents = CubeXType.getSuperTypes(((CubeXClassBase)par).getParentType(), classCon);
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
				}
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub), cbSub);
					CubeXType expType = CubeXType.makeSubstitution(CubeXType.makeSubstitution(exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), sub), cbSub);
					if(!CubeXType.isSubType(expType, tpe, classCon))
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
				
				if(clss.isYielder())
				{
					CubeXType innerType = CubeXType.makeSubstitution(((CubeXTypeIterable)classCon.lookup(clss.getName()).getParentType()).getInnerType(),sub);
					return new CubeXTypeYielder(name, innerType);
				}
				else
					return CubeXTypeClass.NewCubeXTypeClass(name, parameters);
			}
		}
	}
	
	@Override
	public String preC(CubeXProgramPiece par) {
		
		StringBuilder sb = new StringBuilder();
		if(parent!=null)
			 sb.append(parent.preC(par));
			
		if(parent!=null && !simplified) //e.fun();
		{

			 boolean isPrim = parent.getTypeUnsafe().isBool()||parent.getTypeUnsafe().isInt();
			 isPrim=false;
			 tempVar = CUtils.getTempName();
			 
			 if(par!=null)
			 {
				 par.addLocal(tempVar, isPrim);
			 }
			 else
			 {
				 GlobalAwareness.addLocal(tempVar, isPrim);
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
				CubeXClassBase cb = null;
				CubeXFunction fun = null;

				if(parent==null)
				{
					cb=((CubeXTypeClassBase)faketcb).getDeclaration(GlobalContexts.classContext);
					fun=cb.getFunctionContext().lookup(name);
				}
				else
				{
					Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res = parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext);
					cb=res.third.getDeclaration(GlobalContexts.classContext);
					fun=res.second;
				}
	
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
					int i=0;
					for(@SuppressWarnings("unused") CubeXExpression exp : args)
					{
						CubeXType argType = fun.getArglist().get(i).type;
						boolean isPrim = argType.isBool() || argType.isInt();
						isPrim=false;
						if(!isPrim)
						{
							sb.append(", object_t *");
						}
						else
						{
							sb.append(", int");
						}
						i++;
					}
					
					sb.append("))").append("(getMethod(");
					if(parent==null)
						sb.append("this");
					else
						sb.append(CUtils.canonName(tempVar));
					sb.append(", ").append(cb.getID()).append(", ").append(fIndex).append(")))");
					if(parent==null)
						sb.append("(gc_inc(").append("this").append(")");
					else
						sb.append("(gc_inc(").append(CUtils.canonName(tempVar)).append(")");
					
					i=0;
					for(CubeXExpression exp : args)
					{
						CubeXType argType = fun.getArglist().get(i).type;
						boolean isPrim = argType.isBool() || argType.isInt();
						isPrim=false;
						if(!isPrim)
						{
							sb.append(", gc_inc(").append(exp.toC(par)).append(")");
						}
						else
						{
							sb.append(", (int)(").append(exp.toC(par)).append(")");
						}
						
						i++;
					}
					sb.append("))");
					
	
			}
			else if(calltype==CallType.GLOBAL) //Global Function
			{
				CubeXFunction fun =  GlobalContexts.functionContext.lookup(name);
				
				if(fun==null && (par.isFunction() || par.isYielder() )) //Change for interfaces
				{
					if(par.isFunction())
					{
						fun=((CubeXFunction)par).getParent().getFunctionContext().lookup(name);
					}
					else
					{
						fun=((CubeXYielder)par).getParent().getFunctionContext().lookup(name);
					}
				}
				
				sb.append("(").append(CUtils.canonName(fun, false)).append("(");
				String prefix="";
				int i=0;
				for(CubeXExpression exp : args)
				{
					CubeXType argType = fun.getArglist().get(i).type;
					boolean isPrim = argType.isBool() || argType.isInt();
					isPrim=false;
					if(!isPrim)
					{
						sb.append(prefix).append("gc_inc(").append(exp.toC(par)).append(")");
					}
					else
					{
						sb.append(prefix).append("(int)(").append(exp.toC(par)).append(")");
					}
					prefix=", ";
					i++;
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
	public HashSet<CubeXVariable> getUsedVars(boolean globals, HashSet<CubeXFunction> ignoredFunctions) 
	{
		HashSet<CubeXVariable> vars = new HashSet<>();
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
		
		return (parent != null && parent.isFunctionCall()) || isArgFun();
	}

	public CubeXStatement flattenArgs() {
		ArrayList<CubeXExpression> flattenedArgs = new ArrayList<>();
		CubeXBlock flattened = new CubeXBlock();
		for(CubeXExpression arg : args){
			if(arg.isFunctionCall()){
				CubeXExpression originalArg = CubeXFunctionCall.copy((CubeXFunctionCall)arg);
				CubeXAssignment tempVar = new CubeXAssignment(GlobalAwareness.getTempName(), originalArg);
				flattened.add(((CubeXFunctionCall)originalArg).flatten());
				flattened.add(tempVar);
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
	
	public CubeXExpression getParent() {
		return parent;
	}
	public ArrayList<CubeXExpression> getArgs() {
		return args;
	}
	
	public CubeXStatement flatten(){
		CubeXBlock flattened = new CubeXBlock();		

		if(parent != null && parent.isFunctionCall()){
			CubeXFunctionCall par = (CubeXFunctionCall)parent;
			CubeXAssignment tempVar2 = new CubeXAssignment(GlobalAwareness.getTempName(), par);
			flattened.add(par.flatten());
			flattened.add(tempVar2);
			this.parent = tempVar2.getVariable();
		}
		
		if(isArgFun()){
			flattened.add(flattenArgs());
		}
		
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
		
		CubeXFunction fun = null;
		if(calltype.equals(CallType.GLOBAL))
		{
			fun =  GlobalContexts.functionContext.lookup(name);
		}
		else if(calltype.equals(CallType.FUNCTION))
		{
			try
			{
				fun = parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext).second;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			return this;
		}
		
		if(fun.getReturnType().isInt()||fun.getReturnType().isBool())
		{
			return Boxer.boxify(this);
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
					if(args.get(1).isBoolean())
					{
						simplified=true;
						parent = Boxer.unboxify(parent);
						//args.set(0, Boxer.unboxify(args.get(0)));
						//args.set(1, Boxer.unboxify(args.get(1)));
					}
				}
				else if(args.size()==1 && args.get(0).getTypeUnsafe().isInt() && (name.equals("times") || name.equals("plus") || name.equals("minus") || name.equals("equals")))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					//args.set(0, Boxer.unboxify(args.get(0)));
				}
			}
			else if(parent.getTypeUnsafe().isBool())
			{
				if(name.equals("negate"))
				{
					parent = Boxer.unboxify(parent);
					simplified=true;
				}
				else if(args.size()==1 && args.get(0).getTypeUnsafe().isBool() && (name.equals("and") || name.equals("or") || name.equals("equals")))
				{
					simplified=true;
					parent = Boxer.unboxify(parent);
					//args.set(0, Boxer.unboxify(args.get(0)));
				}
			}
		}
		
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).simplifyFunctionBoxes();

			args.set(i, newEntry);
		}
		/*
		if(calltype.equals(CallType.GLOBAL))
		{
			CubeXType retType =  GlobalContexts.functionContext.lookup(name).getReturnType();
			if(retType.isBool() || retType.isInt())
				return Boxer.boxify(this);
		}
		else if(calltype.equals(CallType.FUNCTION))
		{
			try
			{
				CubeXType retType =   parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext).second.getReturnType();
				if(retType.isBool() || retType.isInt())
					return Boxer.boxify(this);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		*/
		return this;
	}

	@Override
	public CubeXExpression primitivifyVariables() {
		
		if(parent!=null)
			parent=parent.primitivifyVariables();
		
		ArrayList<CubeXArgument> argList = null;
		if(calltype.equals(CallType.GLOBAL))
			argList =  GlobalContexts.functionContext.lookup(name).getArglist();
		else if(calltype.equals(CallType.FUNCTION))
		{
			try
			{
				argList = parent.getTypeUnsafe().methodLookup(name, GlobalContexts.classContext).second.getArglist();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			//CubeXClass base = (CubeXClass)GlobalContexts.classContext.lookup(name);
			//argList=base.getConstructorArgs();
		}
		
		for(int i=0; i<args.size(); ++i)
		{
			CubeXExpression newEntry = args.get(i).primitivifyVariables();
			if(argList!=null)
			{
			CubeXType argType = argList.get(i).type;
			boolean isPrim = argType.isBool() || argType.isInt();
			isPrim=false;
			if(isPrim)
				newEntry=Boxer.unboxify(newEntry);
			}
			args.set(i, newEntry);
		}

		
		return this;
	}

	@Override
	public boolean equals(Object other) {
		if(other==null)
			return false;
		if(!(other instanceof CubeXFunctionCall))
			return false;
		
		if(((CubeXFunctionCall) other).isFunctionCall()){
			CubeXFunctionCall oF = (CubeXFunctionCall)other;
			if((args.size() != oF.args.size()) || (parameters.size() != oF.parameters.size()))
				return false;
			
			for(int i = 0; i < args.size(); i++)
				if(!args.get(i).equals(oF.args.get(i)))
					return false;
			
			for(int i = 0; i < parameters.size(); i++)
				if(!parameters.get(i).equals(oF.parameters.get(i)))
					return false;
			
			if(parent == null || oF.parent == null){
				return parent == null && oF.parent == null  && name.equals(oF.name);
			}
			
			return parent.equals(oF.parent) && name.equals(oF.name);
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean contains(CubeXVariable var) {
		return args.contains(var);
	}
	
	@Override
	public void replace(CubeXVariable oldVar, CubeXExpression newVar) {
		ArrayList<CubeXExpression> newArgs = new ArrayList<>();
		for(CubeXExpression arg : args){
			if(arg.equals(oldVar)){
				newArgs.add(newVar);
			}
			else{
				newArgs.add(arg);
			}
		}
		args = newArgs;
	}
}
