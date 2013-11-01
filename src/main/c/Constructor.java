package main.c;

import main.context.GlobalContexts;
import main.exceptions.ContextException;
import main.expression.CubeXExpression;
import main.expression.CubeXVariable;
import main.program.CubeXArgument;
import main.program.CubeXClass;
import main.type.CubeXTypeClass;

public class Constructor {

	private StringBuilder sb;
	
	public Constructor()
	{
		sb = new StringBuilder();
	}
	
	public void addClass(CubeXClass clss) throws ContextException
	{
		StringBuilder sbdecl = new StringBuilder();
		
		sbdecl.append("object_t * c_").append(clss.getName()).append("(");
		
		String prefix="";
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sbdecl.append(prefix).append("object_t *ca_").append(i);
			prefix=", ";
		}
		
		sbdecl.append(")");
		
		sb.append(sbdecl.toString()).append("\n{\n");
		
		GlobalAwareness.declarationAppend(sbdecl.append(";").toString());
		
		
		
		
		sb.append("object_t * __this__ = createObject(").append(clss.getID()).append(", 0)");
		
		sb.append("cint_").append(clss.getName()).append("(__this__");
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sb.append(prefix).append("ca_").append(i);
			prefix=", ";
		}
		sb.append(");\n");
		sb.append("return __this__;\n");
		sb.append("}\n");
		
		
		sbdecl = new StringBuilder();
		
		sbdecl.append("void cint_").append(clss.getName()).append("(object *__this__");
		
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sbdecl.append(", ").append("object_t *ca_").append(i);
		}
		
		sbdecl.append(")\n");
		sb.append(sbdecl).append("{\n");
		
		GlobalAwareness.declarationAppend(sbdecl.append(";").toString());
		
		int i=0;
		for(CubeXArgument arg : clss.getConstructorArgs())
		{
			CubeXVariable var = arg.variable;
			sb.append("*(((object_t **)(this+1))+").append(clss.definedFields.lastIndexOf(var.getName())).append(") = ca_").append(i).append(";\n");
			i++;
		}
		
		CubeXClass superClass = ((CubeXTypeClass)clss.getParentType().getConstructableComponent()).getDeclaration(GlobalContexts.classContext);
		
		for(CubeXExpression exp : clss.getSuperArgs())
		{
			sb.append(exp.preC());
		}
		sb.append("cint_").append(superClass.getName()).append("(__this__");
	
		for(CubeXExpression exp : clss.getSuperArgs())
		{
			sb.append(", ").append(exp.toC());
		}
		sb.append(");");
		sb.append("}\n");
		
	}
	
	public String toC()
	{
		return sb.toString();
	}

}
