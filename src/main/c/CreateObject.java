package main.c;

import main.program.CubeXClass;

public class CreateObject 
{

	private StringBuilder createObjectFun;
	
	public CreateObject()
	{
		createObjectFun=new StringBuilder();
		
		createObjectFun.append("object_t * createObject(int type, unsigned int startingRefs)\r\n" + 
				"{\r\n" + 
				"	object_t * object;\r\n" + 
				"	switch(type)\r\n" + 
				"	{\r\n" + 
				"	case 0: \r\n" + 
				"		object=x3malloc(sizeof(integer_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		((integer_t *)object)->value=0;\r\n" + 
				"		break;\r\n" + 
				"	case 1: \r\n" + 
				"		object=x3malloc(sizeof(boolean_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		((boolean_t *)object)->value=false;\r\n" + 
				"		break;\r\n" + 
				"	case 2:\r\n" + 
				"		object=x3malloc(sizeof(character_t));\r\n" + 
				"		object->numFields=-1;\r\n" + 
				"		((character_t *)object)->value=0;\r\n" + 
				"		break;\r\n" + 
				"	case 3:\r\n" + 
				"		object=x3malloc(sizeof(iterable_t));\r\n" + 
				"		object->numFields=-2;\r\n" + 
				"		object->vTable=NULL;\r\n" + 
				"		break;\r\n\r\n");
		
	}
	
	public void addClass(CubeXClass clss)
	{

		createObjectFun.append("	case ").append(clss.getID()).append(":\r\n" + 
				"		object=x3malloc(sizeof(iterable_t) + ").append(clss.definedFields.size()).append("*sizeof(object_t *));\r\n" + 
				"		object->numFields=").append(clss.definedFields.size()).append(";\r\n" + 
				"		object->vTable=vt_").append(clss.getName()).append(";\r\n" + 
				"		break;\r\n\n");
		
		
	}
	
	public String toC()
	{
		String res = createObjectFun.toString() + 
				"	}\r\n" + 
				"	object->refCount=startingRefs;\r\n" + 
				"	return object;\r\n" + 
				"}\n\n";
		
		return res;
		
	}

}
