package main.c;

import main.context.GlobalContexts;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;

public class Initializer 
{
	
	
	
	private String initVTables() throws TypeCheckException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("void init_initVTables()\n{\n");
		sb.append("vTable_t * vtable;\n"
				+ " iTable_t * itable;\n"
				+ " iTableEntry_t *curEntry;\n"
				+ " func * curVEntry;\n\n");
		
		for(CubeXClassBase cb : GlobalContexts.classContext.getAllClasses())
		{
			if(cb.isInterface())
				continue;
			CubeXClass clss = (CubeXClass)cb;
			VTable clssVTable = clss.generateVTable();
			GlobalAwareness.declarationAppend("vTable_t * vt_"+clss.getName()+";\n");
			sb.append(clssVTable.toC());
		}
		sb.append("}\n");
		
		return sb.toString();
	}
}
