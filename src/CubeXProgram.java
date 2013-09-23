import java.util.ArrayList;


public class CubeXProgram {
	
	private ArrayList<CubeXProgramPiece> pieces;

	public CubeXProgram()
	{
		pieces = new ArrayList<CubeXProgramPiece>();
	}
		
	public void addPiece(CubeXProgramPiece piece)
	{
		pieces.add(piece);
	}
	
	public void addPieces(CubeXProgram program)
	{
		pieces.addAll(program.pieces);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		String prefix="";
		for(CubeXProgramPiece piece : pieces)
		{
			sb.append(prefix).append(piece.toString());
			prefix=" ";
		}
		
		return sb.toString();
	}

}
