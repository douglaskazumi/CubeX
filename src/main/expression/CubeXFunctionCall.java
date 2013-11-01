package main.expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.c.CUtils;
import main.context.*;
import main.exceptions.*;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.type.*;
import main.util.*;
import main.program.*;

public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	private String name;
	private ArrayList<? extends CubeXType> parameters;
	private ArrayList<? extends CubeXExpression> args;
	private String tempVar=null;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<? extends CubeXType> parameters, ArrayList<? extends CubeXExpression> args)
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

	@Override
	protected CubeXType calculateType(boolean force, ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon,  boolean setField, CubeXClassBase par) throws ContextException, TypeCheckException 
	{
		CubeXFunction fun;
	
		//Object function calls
		if(parent!=null)
		{
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
			TypeVarSubstitution classSub = res.first;
			
			Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
			Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
			while(argValuesIt.hasNext())
			{
				CubeXExpression exp = argValuesIt.next();
				CubeXType tpe = CubeXType.makeSubstitution(CubeXType.makeSubstitution(argExpectedTypesIt.next().type, classSub), funSub);
				
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
				fun=funCon.lookup(name);
				if(fun == null){
					throw new ContextException();
				}

				if(fun.getTypes().size()!=this.parameters.size())
					throw new TypeCheckException("Bad number of parameters to global function: " + name + ". Expected: " + fun.getTypes().size() + ". Given: " + this.parameters.size());
				if(fun.getArglist().size()!=this.args.size())
					throw new TypeCheckException("Bad number of arguments to global function: " + name + ". Expected: " + fun.getArglist().size() + ". Given: " + this.args.size());
				
				TypeVarSubstitution sub = new TypeVarSubstitution(fun.getTypes(), parameters);
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub);
					
					if(!CubeXType.isSubType(exp.getType(force, classCon, funCon, varCon, typeVarCon, setField, par), tpe, classCon))
						throw new TypeCheckException("BAD ARGUMENT TO GLOBAL FUNCTION CALL");
				}
				
				return CubeXType.makeSubstitution(fun.getReturnType(), sub);
			}
			else
			//Constructor
			{
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
	public String preC() {
		
		StringBuilder sb = new StringBuilder();
		
		if(parent!=null) //e.fun();
		{
			 sb.append(parent.preC());
			 tempVar = CUtils.getTempName();
			 sb.append("object_t *").append(tempVar).append(" = ").append(parent.toC()).append(";");
		}
		
		for(CubeXExpression exp : args)
		{
			sb.append(exp.preC());
		}
		
		return sb.toString();
	}

	@Override
	public String toC() {
		StringBuilder sb = new StringBuilder();
		try
		{
			if(parent!=null) //e.fun();
			{
	
					Triple<TypeVarSubstitution, CubeXFunction, CubeXTypeClassBase> res =  parent.getType().methodLookup(name, GlobalContexts.classContext);
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
					
					sb.append("((object_t * (*)(object_t *");
					for(@SuppressWarnings("unused") CubeXExpression exp : args)
					{
						sb.append(", ").append("object_t *");
					}
					sb.append("))").append("(getMethod((").append(tempVar).append("), ").append(cb.getID()).append(", ").append(fIndex).append("))");
					sb.append("(").append(tempVar);
					for(CubeXExpression exp : args)
					{
						sb.append(", ").append(exp.toC());
					}
					sb.append("))");
					
	
			}
			else if(name.substring(0, 1).toLowerCase().equals(name.substring(0, 1))) //Global Function
			{
				CubeXFunction fun =  GlobalContexts.functionContext.lookup(name);
				sb.append("(").append(CUtils.canonName(fun, false)).append("(");
				String prefix="";
				for(CubeXExpression exp : args)
				{
					sb.append(prefix).append(exp.toC());
					prefix=", ";
				}
				sb.append("))");
			}
			else //Constructor
			{
				CubeXFunction fun =  GlobalContexts.functionContext.lookup(name);
				sb.append("(").append(CUtils.canonName(fun, true)).append("(");
				String prefix="";
				for(CubeXExpression exp : args)
				{
					sb.append(prefix).append(exp.toC());
					prefix=", ";
				}
				sb.append("))");
			}
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

}
