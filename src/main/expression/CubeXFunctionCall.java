package main.expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXArgument;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.type.CubeXType;
import main.type.CubeXTypeClass;
import main.type.Tuple;
import main.util.TypeVarSubstitution;


public class CubeXFunctionCall extends CubeXExpression 
{
	private CubeXExpression parent;
	private String name;
	private ArrayList<? extends CubeXType> parameters;
	private ArrayList<? extends CubeXExpression> args;
	
	public CubeXFunctionCall(CubeXExpression parent, String name, ArrayList<? extends CubeXType> parameters, ArrayList<? extends CubeXExpression> args)
	{
		this.parent=parent;
		this.name=name;
		if(parameters==null)
		{
			parameters = new ArrayList<CubeXType>();
		}
		this.parameters=parameters;
		if(args==null)
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
		sb.append(parent.toString()).append(" . ").append(name).append(" < ");
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
	protected CubeXType calculateType(ClassContext classCon, FunctionContext funCon, VariableContext varCon, TypeVariableContext typeVarCon) throws ContextException, TypeCheckException 
	{
		CubeXFunction fun;
		//Object function calls
		if(parent!=null)
		{
			CubeXType pType = parent.getType(classCon, funCon, varCon, typeVarCon);
			if(pType.isVariable())
				throw new TypeCheckException();
			
			Tuple<TypeVarSubstitution, CubeXFunction> res = pType.methodLookup(name, classCon);
			fun = res.second;
			if(fun.getTypes().size()!=this.parameters.size())
				throw new TypeCheckException();
			if(fun.getArglist().size()!=this.args.size())
				throw new TypeCheckException();
			
			TypeVarSubstitution funSub = new TypeVarSubstitution(fun.getTypes(), parameters);
			TypeVarSubstitution classSub = res.first;
			
			Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
			Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
			while(argValuesIt.hasNext())
			{
				CubeXExpression exp = argValuesIt.next();
				CubeXType tpe = CubeXType.makeSubstitution(CubeXType.makeSubstitution(argExpectedTypesIt.next().type, classSub), funSub);
				
				if(!CubeXType.isSubType(exp.getType(classCon, funCon, varCon, typeVarCon), tpe))
					throw new TypeCheckException();
			}
			
			return CubeXType.makeSubstitution(CubeXType.makeSubstitution(fun.getReturnType(), classSub), funSub);
		}
		else
		{
			//Global function call
			if(name.toLowerCase().equals(name))
			{
				fun=funCon.lookup(name);
				if(fun == null){
					throw new ContextException();
				}

				if(fun.getTypes().size()!=this.parameters.size())
					throw new TypeCheckException();
				if(fun.getArglist().size()!=this.args.size())
					throw new TypeCheckException();
				
				TypeVarSubstitution sub = new TypeVarSubstitution(fun.getTypes(), parameters);
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = fun.getArglist().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub);
					
					if(!CubeXType.isSubType(exp.getType(classCon, funCon, varCon, typeVarCon), tpe))
						throw new TypeCheckException();
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
					throw new TypeCheckException();
				if(clss.getConstructorArgs().size()!=this.args.size())
					throw new TypeCheckException();
				
				TypeVarSubstitution sub = new TypeVarSubstitution(clss.getTypes(), parameters);
				
				Iterator<? extends CubeXExpression> argValuesIt = args.iterator();
				Iterator<? extends CubeXArgument> argExpectedTypesIt = clss.getConstructorArgs().iterator();
				while(argValuesIt.hasNext())
				{
					CubeXExpression exp = argValuesIt.next();
					CubeXType tpe = CubeXType.makeSubstitution(argExpectedTypesIt.next().type, sub);
					
					if(!CubeXType.isSubType(exp.getType(classCon, funCon, varCon, typeVarCon), tpe))
						throw new TypeCheckException();
				}
				
				return new CubeXTypeClass(name, parameters);
			}
		}
	}

}
