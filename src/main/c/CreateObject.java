package main.c;

import main.program.CubeXClass;

public class CreateObject 
{

	private StringBuilder createObjectFun;
	
	public CreateObject()
	{
		createObjectFun=new StringBuilder();
		
		createObjectFun.append("object_t * createObject(int type, int startingRefs)\r\n" + 
				"{\r\n" + 
				"	int numfields=-1;\r\n" +
				"	int i=0;\r\n" +
				"	object_t * object;\r\n" + 
				"	switch(type)\r\n" + 
				"	{\r\n" + 
				"	case 0: \r\n" + 
				"		object=x3malloc(sizeof(integer_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		object->vTable=vt_Integer;\r\n" + 
				"		((integer_t *)object)->value=0;\r\n" + 
				"		break;\r\n" + 
				"	case 1: \r\n" + 
				"		object=x3malloc(sizeof(boolean_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		object->vTable=vt_Boolean;\r\n" + 
				"		((boolean_t *)object)->value=false;\r\n" + 
				"		break;\r\n" + 
				"	case 2:\r\n" + 
				"		object=x3malloc(sizeof(character_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		object->vTable=vt_Character;\r\n" + 
				"		((character_t *)object)->value=0;\r\n" + 
				"		break;\r\n" + 
				"	case 3:\r\n" + 
				"		object=x3malloc(sizeof(iterable_t));\r\n" + 
				"		object->numFields=-2;\r\n" + 
				"		object->vTable=vt_Iterable;\r\n" + 
				"		break;\r\n"
				+ "	case 4:\r\n" + 
				"		object=x3malloc(sizeof(iterable_t));\r\n" + 
				"		object->numFields=-2;\r\n" + 
				"		object->vTable=vt_String;\r\n" + 
				"		break;\r\n\r\n");
		
	}
	
	public void addClass(CubeXClass clss)
	{

		createObjectFun.append("	case ").append(clss.getID()).append(": /*").append(clss.getName()).append("*/\r\n" + 
				"		object=x3malloc(sizeof(object_t) + ").append(clss.definedFields.size()).append("*sizeof(object_t *));\r\n" + 
				"		numfields = ").append(clss.definedFields.size()).append(";\r\n" +
				"		object->numFields=numfields;\r\n" +
				"		object->vTable=vt_").append(clss.getName()).append(";\r\n" + 
				"		break;\r\n\n");
		
		
	}
	
	public String toC()
	{
		String res = createObjectFun.toString() + 
				"	}\r\n" + 
				"	object->refCount=startingRefs;\r\n" + 
				"	if(numfields==-1)\r\n" +
				"		return object;\r\n" +
				"	for(i=0; i<numfields; ++i)\r\n" +
				"		*(((int *)(((object_t *)object)+1))+i)=NULL;\r\n"	+
				"	return object;\r\n" + 
				"}\n\n";
		
		return res;
		
	}

}
