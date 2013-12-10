package main.c;

import main.program.CubeXClassYielder;
import main.program.CubeXYielder;

public class YielderStructs {
	
	private StringBuilder yieldstructs  = new StringBuilder();
	private StringBuilder yieldCreator = new StringBuilder();
	
	public YielderStructs()
	{
	
		yieldCreator.append("yielder_t * createYielder(int type)\r\n" + 
				"{\r\n" + 
				"	yielder_t *yielder;\r\n"
				+ " int i;\r\n" + 
				"	switch(type)\r\n" + 
				"	{\r\n");
	}
/*
 * declaration of structures
 * 
 * status -1 = done; 0= not started; i = go to yield with id i
 * locals (as set in "locals" variable in CubeXYielder 
 * 
 * This needs to be generated for every yielder/yielder class.
 * 
 * 
 */
	
	public void addYielder(CubeXClassYielder yielderclss)
	{
		CubeXYielder yielder = yielderclss.getYielder();
		
		yieldstructs.append("typedef struct { \n int status;\n object_t *returnValue;\n int numVars;\n");
		for(String var: yielder.locals.keySet())
		{
			yieldstructs.append("object_t *var_").append(CUtils.canonName(var)).append(";\n");
		}
		yieldstructs.append(" } yielder_").append(yielderclss.getName()).append("_t;\n\n");
		
		
		
		yieldCreator.append("		case ").append(yielderclss.myTypeID).append(":\r\n" + 
		"			yielder=(yielder_t *)x3malloc(sizeof(yielder_").append(yielderclss.getName()).append("_t));\r\n" + 
		"			yielder->status=0;\r\n" + 
		"			yielder->returnValue=NULL;\r\n" + 
		"			yielder->numVars=").append(yielderclss.getYielder().locals.size()).append(";\r\n" + 
		"			break;\r\n") ;
	}
	
	public String getStructs()
	{
		return yieldstructs.toString();
	}
	
	public String getYieldFunctions()
	{
		yieldCreator.append("	}\r\n	"
		+ "for(i=0; i<yielder->numVars; ++i)\r\n" + 
		"		*(((int *)(yielder+1))+i)=NULL;" + 
		"	return yielder;\r\n" + 
		"}\n\n");
		
		return yieldCreator.toString();
	}
}
