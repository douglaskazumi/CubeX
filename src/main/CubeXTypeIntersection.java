package main;

public class CubeXTypeIntersection extends CubeXType {

	private CubeXType A;
	private CubeXType B;
	
	public CubeXTypeIntersection(CubeXType A, CubeXType B) 
	{
		this.A=A;
		this.B=B;
	}
	
	public boolean isIntersection()
	{
		return true;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(A.toString()).append(" & ").append(B.toString());
		return sb.toString();
	}

}
