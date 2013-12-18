package main.c;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import main.context.GlobalContexts;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.util.CubeXCompiler;


public class Initializer 
{
	
	
	public String initVTables() throws TypeCheckException
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
		
		sb.append("void gc_allVTable()\r\n{\n");
		
		for(CubeXClassBase cb : GlobalContexts.classContext.getAllClasses())
		{
			if(cb.isInterface())
				continue;
			CubeXClass clss = (CubeXClass)cb;
			sb.append("gc_vTable(vt_").append(clss.getName()).append(");\n");
		}

		sb.append("	return;\r\n}\n");	
		return sb.toString();
	}
	
	public String init() throws TypeCheckException, IOException
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(GlobalAwareness.getCreateObj());
		BufferedReader bf;
		if(CubeXCompiler.optimizations)
			bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("main/c/cubex_lib.c")));
		else
			bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("main/c/cubex_lib_unopt.c")));
		
		String line = bf.readLine();
		while(line!=null)
		{
			sb.append(line).append("\n");
			line=bf.readLine();
		}
		bf.close();
		
		sb.append(GlobalAwareness.getConstructors());
		
		String headerFile;
		
		if(CubeXCompiler.optimizations)
			headerFile = "main/c/cubex_lib.h";
		else
			headerFile = "main/c/cubex_lib_unopt.h";
			
		copyFile(getClass().getClassLoader().getResourceAsStream(headerFile), new File("cubex_lib.h"));

		return sb.toString();
	}
	
	public static void copyFile(InputStream sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    Channel source = null;
	    FileChannel destination = null;

	    try {
	        source = Channels.newChannel(sourceFile);
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom((ReadableByteChannel) source, 0, Integer.MAX_VALUE);
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
}
