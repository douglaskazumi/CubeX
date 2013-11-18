package main.Optimizations;

import java.util.ArrayList;
import java.util.LinkedList;

import main.c.GlobalAwareness;
import main.context.GlobalContexts;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXProgramPiece;
import main.statement.CubeXStatement;
import main.util.CubeXProgram;

public class LiveVariableAnalysis
{
	CubeXProgram prog;

	public LiveVariableAnalysis(CubeXProgram prog)
	{
		this.prog = prog;
	}

	
	public void analyze()
	{
		prog.initCFG();
		
		LinkedList<CubeXProgramPiece> workQueue = new LinkedList<>();
		workQueue.addAll(GlobalAwareness.allNode);
		
		for(CubeXClassBase cb : GlobalContexts.classContext.getAllClasses())
		{
			if(!cb.isClass())
				continue;
			
			CubeXClass clss = (CubeXClass)cb;
			CubeXStatement stat = clss.getLastStatement();
			if(stat!=null)
				workQueue.add(stat);
		}
		
		while(!workQueue.isEmpty())
		{
			CubeXProgramPiece piece = workQueue.removeFirst();
			boolean changed = piece.doLiveVariableAnalysisStep(piece.isTopLevel());
			if(changed)
			{
				workQueue.addAll(piece.getPredecesors());
			}
		}
		
		prog.updateDeadVariables();
	}
}
