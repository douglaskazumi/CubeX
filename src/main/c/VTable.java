package main.c;

import java.util.ArrayList;

import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;

public class VTable {
	
	InterfaceTable iTable;
	ArrayList<VTableEntry> entries;
	
	int numSinceLastAdd=0;

	private VTable()
	{
		iTable= new InterfaceTable();

	}
	
	public static VTable getVTable(CubeXClass clss) throws TypeCheckException
	{
		VTable vTable = new VTable();
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
		entries.add(new VTableEntry(fun));
		numSinceLastAdd+=1;
	}
	
	
	
}

class VTableEntry {
	
	private CubeXFunction func;

	public VTableEntry(CubeXFunction fun)
	{
		func=fun;
	}
	

}