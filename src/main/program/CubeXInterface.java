package main.program;
import java.util.ArrayList;

import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.type.CubeXType;
import main.type.CubeXTypeVariable;
import main.util.Tuple;


public class CubeXInterface extends CubeXClassBase {

	
	public boolean isInterface()
	{
		return true;
	}
	
	
	public CubeXInterface(String name, ArrayList<CubeXTypeVariable> types, CubeXType parentType, ArrayList<CubeXFunction> functions)
	{
		super(name, types,parentType,functions);
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
	public Tuple<Boolean, CubeXType> typecheck(ClassContext classCon,FunctionContext funCon, VariableContext varCon,TypeVariableContext typeVarCon) throws ContextException,TypeCheckException 
	{
		if (classCon.lookup(name)!=null)
			throw new ContextException();
	
		TypeVariableContext classTypeVarCon = (TypeVariableContext)typeVarCon.createChildContext();
		parentType = CubeXType.validateType(parentType, true, classCon, typeVarCon);

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
			innerFunCon.add(f.getName(), f);
			
		}
				
		this.myFunctionContext=innerFunCon;
		
		for(CubeXFunction f : functions)
		{
			if(!f.isDeclaration())
				f.typecheck(classCon, innerFunCon, newVarCon, classTypeVarCon);
		}
		

		
		return null;
	}

}
