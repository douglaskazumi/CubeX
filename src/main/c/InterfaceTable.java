package main.c;

import java.util.ArrayList;

import main.program.CubeXClassBase;

public class InterfaceTable {

	public ArrayList<InterfaceTableEntry> entries;
	
	private int currOffset;
	
	public InterfaceTable()
	{
		entries = new ArrayList<InterfaceTableEntry>();
		currOffset=1;
	}
	
	public void addEntry(CubeXClassBase cb, int numEntries)
	{
		entries.add(new InterfaceTableEntry(cb, currOffset));
		currOffset+=numEntries;
	}

}

class InterfaceTableEntry
{
	public CubeXClassBase classBase;
	public int offset;
	
	public InterfaceTableEntry(CubeXClassBase cb, int off)
	{
		classBase=cb;
		offset=off;
	}
}