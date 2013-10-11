package main.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.context.*;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXVariable;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.type.CubeXTypeVariable;

public class CubeXProgram {

	private ArrayList<CubeXProgramPiece> pieces;

	public CubeXProgram() {
		pieces = new ArrayList<CubeXProgramPiece>();
	}

	public void addPiece(CubeXProgramPiece piece) {
		pieces.add(piece);
	}

	public void addPieces(CubeXProgram program) {
		pieces.addAll(program.pieces);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		String prefix = "";
		for (CubeXProgramPiece piece : pieces) {
			sb.append(prefix).append(piece.toString());
			prefix = " ";
		}

		return sb.toString();
	}

	private void initializeContexts() throws ContextException {
		GlobalContexts.classContext = new ClassContext(null);
		GlobalContexts.functionContext = new FunctionContext(null);
		GlobalContexts.variableContext = new VariableContext(null);
		ArrayList<CubeXFunction> funs;
		CubeXClass newClass;

		// Iterable
		newClass = new CubeXClass("Iterable", new ArrayList<CubeXTypeVariable>(Arrays.asList(new CubeXTypeVariable("E"))), null, CubeXType.getThing(), null, null, null);
		newClass.setFunctionContextManual(new ArrayList<CubeXFunction>(), GlobalContexts.functionContext);
		GlobalContexts.classContext.add(newClass.getName(),newClass);

		// Boolean
		funs = defineBooleanFunctions();
		newClass = new CubeXClass("Boolean", null, null, CubeXType.getThing(), null, null, funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		// Integer
		funs = defineIntegerFunctions();
		newClass = new CubeXClass("Integer", null, null, CubeXType.getThing(), null, null, funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		//Character
		funs = defineCharFunctions();
		newClass = new CubeXClass("Character", null, null, CubeXType.getThing(), null, null, funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		//String
		funs = defineStringFunctions();
		newClass = new CubeXClass("String", null, null, new CubeXTypeIterable(CubeXType.getCharacter()), null, null, funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		//Functions
		defineGlobalFunctions();
		
		//Variables
		GlobalContexts.variableContext.add("input", new CubeXTypeIterable(CubeXType.getString()));
	}

	public void defineGlobalFunctions() throws ContextException {
		CubeXFunction newFunction;
		CubeXScheme newScheme;

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("unicode"), CubeXType.getInteger())
											)), 
									CubeXType.getCharacter());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("character", newScheme));
		GlobalContexts.functionContext.add(newFunction.getName(), newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("characters"), new CubeXTypeIterable(CubeXType.getCharacter()))
											)), 
									CubeXType.getString());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("string", newScheme));
		GlobalContexts.functionContext.add(newFunction.getName(), newFunction);
	}
	
	private ArrayList<CubeXFunction> defineStringFunctions() {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getString())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("equals", newScheme));
		functions.add(newFunction);
		
		return functions;
	}
	
	private ArrayList<CubeXFunction> defineCharFunctions() {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("unicode", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getCharacter())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("equals", newScheme));
		functions.add(newFunction);
		
		return functions;
	}
	
	private ArrayList<CubeXFunction> defineIntegerFunctions() {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("negative", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("factor"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("times", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("divisor"), CubeXType.getInteger())
											)), 
									new CubeXTypeIterable(CubeXType.getInteger()));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("divide", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("modulus"), CubeXType.getInteger())
											)), 
									new CubeXTypeIterable(CubeXType.getInteger()));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("modulo", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("summand"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("plus", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("subtrahend"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("minus", newScheme));
		functions.add(newFunction);
		
		commonMethods(functions,CubeXType.getInteger());

		return functions;
	}

	private ArrayList<CubeXFunction> defineBooleanFunctions() {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("negate", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("and", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("or", newScheme));
		functions.add(newFunction);

		commonMethods(functions,CubeXType.getBoolean());
		
		return functions;
	}

	public void commonMethods(ArrayList<CubeXFunction> functions, CubeXType T) {
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("upper"), CubeXType.getBoolean()), 
													new CubeXArgument(new CubeXVariable("includeLower"), CubeXType.getBoolean()), 
													new CubeXArgument(new CubeXVariable("includeUpper"), CubeXType.getBoolean())
											)), 
									new CubeXTypeIterable(T));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("through", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("inclusive"), CubeXType.getBoolean())
											)), 
									new CubeXTypeIterable(T));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("onwards", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean()), 
													new CubeXArgument(new CubeXVariable("strict"), CubeXType.getBoolean())
											)), 
									T);
		newFunction = new CubeXFunction(new CubeXFunctionHeader("lessThan", newScheme));
		functions.add(newFunction);

		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean())
											)), 
									T);
		newFunction = new CubeXFunction(new CubeXFunctionHeader("equals", newScheme));
		functions.add(newFunction);
	}

	public boolean typeCheck(){
		try {
			initializeContexts();
		} catch (ContextException e1) {
			// Should never fall here
			e1.printStackTrace();
		}

		try {
			Iterator<CubeXProgramPiece> piecesIt = pieces.iterator();
			boolean wasFunction = false;
			ArrayList<CubeXFunction> curFunSet = new ArrayList<CubeXFunction>();
			
			boolean lastDidReturn=false;
			
			while (piecesIt.hasNext()) {
				CubeXProgramPiece piece = piecesIt.next();
				lastDidReturn=false;
				if (piece.isFunction()) {
					curFunSet.add((CubeXFunction) piece);
					wasFunction = true;
					continue;
				}
				checkFunctionBlock(wasFunction, curFunSet);
				
				if(piece.isStatement())
				{
					CubeXStatement stat = (CubeXStatement)piece;
					lastDidReturn=stat.typecheck(GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null)).first;
				}
				
				wasFunction = false;
			}

			// shouldn't need this
			checkFunctionBlock(wasFunction, curFunSet);
			
			if(!lastDidReturn)
				throw new TypeCheckException();
		} catch (Exception e) {
			System.out.println(e.toString());
			if(e.getMessage()!=null)
				System.out.println(e.getMessage());
			for(StackTraceElement el : e.getStackTrace())
			{
				System.out.println(el.toString());
			}
			return false;
		}

		return true;
	}

	private void checkFunctionBlock(boolean wasFunction, ArrayList<CubeXFunction> curFunSet) throws ContextException, TypeCheckException {
		if (wasFunction) {
			for (CubeXFunction f : curFunSet) {
				GlobalContexts.functionContext.add(f.getName(), f);
			}
			for (CubeXFunction f : curFunSet) {
				f.typecheck(GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null));
			}
			curFunSet.clear();
		}

	}

}
