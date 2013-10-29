package main.c;


public class GlobalAwareness {
	private static StringBuilder declarations = new StringBuilder();
	private static StringBuilder code = new StringBuilder();
	private static int tabs = 0;
	
	public static void declarationAppend(String newLine){
		declarations.append(newLine);
		declarations.append(System.getProperty("line.separator"));
	}
	
	public static void codeAppend(String newLine) {
		if(newLine.contains("}")){
			tabs -= 1;
		}
		
		for(int i = 0; i < tabs; i++){
			code.append("\t");
		}
		
		code.append(newLine);
		code.append(System.getProperty("line.separator"));
		
		if(newLine.contains("{")){
			tabs += 1;
		}
		
		//Adds new line after closing brackets
		if(newLine.contains("}")){
			code.append(System.getProperty("line.separator"));
		}
	}

	
}
