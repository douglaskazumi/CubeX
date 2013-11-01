package main.c;

import java.util.ArrayList;

import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;

public class VTable {
	
	private InterfaceTable iTable;
	private ArrayList<CubeXFunction> entries;
	private CubeXClass host;	
	private int numSinceLastAdd=0;

	private VTable(CubeXClass clss)
	{
		iTable= new InterfaceTable();
		host = clss;
	}
	
	public static VTable getVTable(CubeXClass clss) throws TypeCheckException
	{
		VTable vTable = new VTable(clss);
		try
		{
			clss.populateVTable(vTable, clss, false);
		} catch (ContextException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vTable;
	}
	
	public void finishBase(CubeXClassBase cb)
	{
		iTable.addEntry(cb, numSinceLastAdd);
		numSinceLastAdd=0;
	}
	
	public void addEntry(CubeXFunction fun)
	{
		entries.add(fun);
		numSinceLastAdd+=1;
	}
	
	/*
	 * variable to be declared before this block
	 * vTable_t * vtable;
	 * iTable_t * itable;
	 * iTableEntry_t *curEntry;
	 * func * curVEntry;
	 * 
	 * GLOBALS VARIABLE vTable_t * vt_CLASSNAME; for each class
	 * 
	 */
	public String toC()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("vtable = x3malloc(sizeof(vTable_t) + ").append(entries.size()).append("*sizeof(func));\n");
		sb.append("itable = x3malloc(sizeof(iTable_t) + ").append(iTable.getSize()).append("*sizeof(iTableEntry_t));\n");
		sb.append("itable->numEntries = ").append(iTable.getSize()).append(";\n");
		sb.append("curEntry = (iTableEntry_t *)(itable+1);\n");
		
		for(InterfaceTableEntry entry : iTable.entries)
		{
			sb.append("curEntry->typeID = ").append(entry.classBase.getID()).append(";\n");
			sb.append("curEntry->functionIndex = ").append(entry.offset).append(";\n");
			sb.append("curEntry++;\n");
		}
		
		sb.append("vtable->iTable = itable;\n");
		sb.append("curVEntry = (func *)(vtable+1);\n");
		
		for(CubeXFunction fun : entries)
		{
			sb.append("*(curVEntry)=(func)(").append(CUtils.canonName(fun, false)).append(");\n");
			sb.append("curVEntry++;\n");
		}
		
		sb.append("vt_").append(host.getName()).append(" = vtable;\n");
		return sb.toString();
	}
	
	
	
}
