package main.Optimizations;

import java.util.ArrayList;
import java.util.LinkedList;

import main.program.CubeXProgramPiece;
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
		ArrayList<CubeXProgramPiece> returns = prog.initCFG();
		LinkedList<CubeXProgramPiece> workQueue = new LinkedList<>();
		workQueue.addAll(returns);
		
		while(!workQueue.isEmpty())
		{
			CubeXProgramPiece piece = workQueue.removeFirst();
			boolean changed = piece.doLiveVariableAnalysisStep();
			if(changed)
			{
				workQueue.addAll(piece.getPredecesors());
			}
		}
		
	}
}
