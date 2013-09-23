import java.util.ArrayList;


public class CubeXScheme {

	
	public ArrayList<CubeXType> types;
	public ArrayList<CubeXArgument> arglist;
	public CubeXType returnType;
	
	public CubeXScheme(ArrayList<CubeXType> types, ArrayList<CubeXArgument> arglist, CubeXType returnType)
	{
		this.types = types;
		this.arglist = arglist;
		this.returnType = returnType;
	}

}
