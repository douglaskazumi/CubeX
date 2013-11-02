package main.c;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.context.GlobalContexts;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;


public class Initializer 
{
	
	
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
	
	public String init() throws TypeCheckException, IOException
	{
		StringBuilder sb = new StringBuilder();
	
		sb.append(GlobalAwareness.getConstructors());
		sb.append(GlobalAwareness.getCreateObj());
		sb.append(initVTables());
		
		BufferedReader bf = new BufferedReader(new FileReader("src/main/c/cubex_lib.c"));
		
		String line = bf.readLine();
		while(line!=null)
		{
			sb.append(line).append("\n");
			line=bf.readLine();
		}
		bf.close();
		return sb.toString();
	}
}
