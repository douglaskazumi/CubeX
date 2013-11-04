package main.c;

import main.context.GlobalContexts;
import main.exceptions.ContextException;
import main.expression.CubeXExpression;
import main.expression.CubeXVariable;
import main.program.CubeXArgument;
import main.program.CubeXClass;
import main.program.CubeXFunction;
import main.program.CubeXFunctionHeader;
import main.program.CubeXScheme;
import main.statement.CubeXStatement;
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
		StringBuilder sbafter = new StringBuilder();
		
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
		
		
		sb.append("object_t * this = createObject(").append(clss.getID()).append(", 0);\n");
		
		sb.append("cint_").append(clss.getName()).append("(gc_inc(this)");
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sb.append(prefix).append("ca_").append(i);
			prefix=", ";
		}
		sb.append(");\n");
		
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sb.append("\tgc(gc_dec(ca_").append(i).append("));\n");
		}
		sb.append("return gc_dec(this);\n");
		sb.append("}\n\n");
		
		
		sbdecl = new StringBuilder();
		
		sbdecl.append("void cint_").append(clss.getName()).append("(object_t *this");
		
		for(int i=0; i<clss.getConstructorArgs().size(); ++i)
		{
			sbdecl.append(", ").append("object_t *ca_").append(i);
		}
		
		sbdecl.append(")");
		sb.append(sbdecl).append("\n{\n");
		
		GlobalAwareness.declarationAppend(sbdecl.append(";").toString());
		
		StringBuilder fields = new StringBuilder();
		int i=0;
		for(CubeXArgument arg : clss.getConstructorArgs())
		{
			CubeXVariable var = arg.variable;
			fields.append("*(((object_t **)(this+1))+").append(clss.definedFields.lastIndexOf(var.getName())).append(") = ca_").append(i).append(";\n");
			i++;
		}
		
		CubeXFunction dummyFunction = new CubeXFunction(new CubeXFunctionHeader("test", new CubeXScheme(null, null, null)));
		
		StringBuilder statements = new StringBuilder();
		for(CubeXStatement stat: clss.getStatements())	
		{
			if(stat.isReturn())
				continue;
			String pre = stat.preC(dummyFunction);
			if(!pre.isEmpty())
				statements.append(pre);
			statements.append(stat.toC(dummyFunction));
		}
		
		StringBuilder superCall = new StringBuilder();
		if(clss.getParentType().isClass())
		{
			CubeXClass superClass = ((CubeXTypeClass)(clss.getParentType().getConstructableComponent())).getDeclaration(GlobalContexts.classContext);

			for(CubeXExpression exp : clss.getSuperArgs())
			{
				superCall.append(exp.preC(dummyFunction));
			}
			superCall.append("cint_").append(superClass.getName()).append("(this");
		
			for(CubeXExpression exp : clss.getSuperArgs())
			{
				superCall.append(", ").append(exp.toC(dummyFunction));
			}
			superCall.append(");\n");
			
		}
		
		for(String lvar : dummyFunction.locals)
		{
			sb.append("\tobject_t * ").append(CUtils.canonName(lvar)).append("= NULL;\n");
		}
		
		sb.append(fields).append(statements).append(superCall);
		
		for(String lvar : dummyFunction.locals)
		{
			sb.append("\tgc(gc_dec(").append(CUtils.canonName(lvar)).append("));\n");
		}
		
		sb.append("}\n\n");
		
	}
	
	public String toC()
	{
		return sb.toString();
	}

}
