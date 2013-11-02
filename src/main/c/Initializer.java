package main.c;


import main.context.GlobalContexts;

import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;


public class Initializer 
{
	
	
	private String getMain()
	{
		StringBuilder main = new StringBuilder();
		
		main.append("int main(int argc, char* argv[])");
		main.append("{\n");
		main.append("\tvoid initialize(argc, argv);\n");
		main.append("\tvoid init_VTables();\n\n");

		
		main.append("\tcubex_main();\n");
		main.append("\treturn 0;");
		main.append("}\n");
		
		return main.toString();
	}
	
	
	private String initVTables() throws TypeCheckException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("void init_VTables()\n{\n");
		sb.append("\tvTable_t * vtable;\n"
				+ "\tiTable_t * itable;\n"
				+ "\tiTableEntry_t *curEntry;\n"
				+ "\tfunc * curVEntry;\n\n");
		
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
	
	public String init() throws TypeCheckException
	{
		StringBuilder sb = new StringBuilder();
	
		sb.append(GlobalAwareness.getConstructors());
		sb.append(GlobalAwareness.getCreateObj());
		sb.append(initVTables());
		sb.append(getMain());
		
		return sb.toString();
	}
}
