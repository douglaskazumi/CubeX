package main.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import main.Optimizations.ExpressionContext;
import main.c.CUtils;
import main.c.GlobalAwareness;
import main.c.Initializer;
import main.context.ClassContext;
import main.context.FunctionContext;
import main.context.GlobalContexts;
import main.context.TypeVariableContext;
import main.context.VariableContext;
import main.exceptions.ContextException;
import main.exceptions.TypeCheckException;
import main.expression.CubeXVariable;
import main.program.CubeXClass;
import main.program.CubeXClassBase;
import main.program.CubeXFunction;
import main.program.CubeXProgramPiece;
import main.statement.CubeXBlock;
import main.statement.CubeXStatement;
import main.type.CubeXType;
import main.type.CubeXTypeIterable;
import main.type.CubeXTypeVariable;

public class CubeXProgram {

	private ArrayList<CubeXProgramPiece> pieces;
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public CubeXProgram() {
		pieces = new ArrayList<CubeXProgramPiece>();
	}

	public void addPieces(CubeXProgram program) {
		pieces.addAll(program.pieces);
	}

	public void addPiece(CubeXProgramPiece piece) {
		pieces.add(piece);
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
		newClass.myTypeID=3;
		GlobalContexts.classContext.add(newClass.getName(),newClass);
	
		// Boolean
		newClass = new CubeXClass("Boolean", null, null, CubeXType.getThing(), null, null, null);
		funs = defineBooleanFunctions(newClass);
		newClass.setFunctions(funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		newClass.myTypeID=1;
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		// Integer
		newClass = new CubeXClass("Integer", null, null, CubeXType.getThing(), null, null, null);
		funs = defineIntegerFunctions(newClass);
		newClass.setFunctions(funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		newClass.myTypeID=0;
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		//Character
		newClass = new CubeXClass("Character", null, null, CubeXType.getThing(), null, null, null);
		funs = defineCharFunctions(newClass);
		newClass.setFunctions(funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		newClass.myTypeID=2;
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		//String
		newClass = new CubeXClass("String", null, null, new CubeXTypeIterable(CubeXType.getCharacter()), null, null, null);
		funs = defineStringFunctions(newClass);
		newClass.setFunctions(funs);
		newClass.setFunctionContextManual(funs, GlobalContexts.functionContext);
		newClass.myTypeID=3;
		GlobalContexts.classContext.add(newClass.getName(),newClass);
		
		CubeXClassBase.curTypeID=4;
		
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

	private ArrayList<CubeXFunction> defineStringFunctions(CubeXClass clss) {
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
		newFunction.setParent(clss);
		functions.add(newFunction);
		
		
		return functions;
	}

	private ArrayList<CubeXFunction> defineCharFunctions(CubeXClass clss) {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("unicode", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getCharacter())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("equals", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
		
		return functions;
	}

	private ArrayList<CubeXFunction> defineIntegerFunctions(CubeXClass clss) {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("negative", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("factor"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("times", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("divisor"), CubeXType.getInteger())
											)), 
									new CubeXTypeIterable(CubeXType.getInteger()));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("divide", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("modulus"), CubeXType.getInteger())
											)), 
									new CubeXTypeIterable(CubeXType.getInteger()));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("modulo", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("summand"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("plus", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("subtrahend"), CubeXType.getInteger())
											)), 
									CubeXType.getInteger());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("minus", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
		
		commonMethods(functions,CubeXType.getInteger(),clss);
	
		return functions;
	}

	private ArrayList<CubeXFunction> defineBooleanFunctions(CubeXClass clss) {
		ArrayList<CubeXFunction> functions = new ArrayList<CubeXFunction>();
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		
		newScheme = new CubeXScheme(null, null, CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("negate", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("and", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), CubeXType.getBoolean())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("or", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		commonMethods(functions,CubeXType.getBoolean(), clss);
		
		return functions;
	}

	public void commonMethods(ArrayList<CubeXFunction> functions, CubeXType T, CubeXClass clss) {
		CubeXFunction newFunction;
		CubeXScheme newScheme;
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("upper"), T), 
													new CubeXArgument(new CubeXVariable("includeLower"), CubeXType.getBoolean()), 
													new CubeXArgument(new CubeXVariable("includeUpper"), CubeXType.getBoolean())
											)), 
									new CubeXTypeIterable(T));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("through", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("inclusive"), CubeXType.getBoolean())
											)), 
									new CubeXTypeIterable(T));
		newFunction = new CubeXFunction(new CubeXFunctionHeader("onwards", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), T), 
													new CubeXArgument(new CubeXVariable("strict"), CubeXType.getBoolean())
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("lessThan", newScheme));
		newFunction.setParent(clss);
		functions.add(newFunction);
	
		newScheme = new CubeXScheme(null, 
									new ArrayList<CubeXArgument>(
											Arrays.asList(
													new CubeXArgument(new CubeXVariable("that"), T)
											)), 
									CubeXType.getBoolean());
		newFunction = new CubeXFunction(new CubeXFunctionHeader("equals", newScheme));
		newFunction.setParent(clss);
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
			boolean wasStatement =false;
			ArrayList<CubeXFunction> curFunSet = new ArrayList<CubeXFunction>();
			
			Tuple<Boolean, CubeXType> lastDidReturn = null;
			
			while (piecesIt.hasNext()) {
				CubeXProgramPiece piece = piecesIt.next();
				lastDidReturn=new Tuple<Boolean, CubeXType>(false, null);
				if (piece.isFunction()) {
					curFunSet.add((CubeXFunction) piece);
					wasFunction = true;
					continue;
				}
				checkFunctionBlock(wasFunction, curFunSet);
				
				if(piece.isStatement())
				{
					wasStatement=true;
					CubeXStatement stat = (CubeXStatement)piece;
					lastDidReturn=stat.typecheck(false, GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null), false, null, null);
					if(lastDidReturn.first && (!lastDidReturn.second.isIterable() || !((((CubeXTypeIterable)lastDidReturn.second).getInnerType().isString())||((CubeXTypeIterable)lastDidReturn.second).getInnerType().isNothing())))
						throw new TypeCheckException("Final return not a Iterable<String>");
				}
				else
				{
					if(wasStatement)
					{
						wasFunction=false;
						GlobalContexts.variableContext.setMutable(false);
						GlobalContexts.variableContext=GlobalContexts.variableContext.createChildContext();
					}
					piece.typecheck(false, GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null), false, null, null);
				}
				
				wasFunction = false;
			}
	
			// shouldn't need this
			checkFunctionBlock(wasFunction, curFunSet);
			
			if(!lastDidReturn.first || !lastDidReturn.second.isIterable() || !((((CubeXTypeIterable)lastDidReturn.second).getInnerType().isString())||((CubeXTypeIterable)lastDidReturn.second).getInnerType().isNothing()))
				throw new TypeCheckException("Final return not a Iterable<String>");
		} catch (Exception e) {
			if(CubeXCompiler.debug)
			{
				errorMsg = e.getMessage();
				System.out.println(e.toString());
				for(StackTraceElement el : e.getStackTrace())
				{
					System.out.println(el.toString());
				}
			}
			/**/
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
				f.typecheck(false, GlobalContexts.classContext, GlobalContexts.functionContext, GlobalContexts.variableContext, new TypeVariableContext(null), false, null, null);
			}
			curFunSet.clear();
		}
	
	}
	
	public String toC() throws TypeCheckException, IOException, ContextException 
	{
		Initializer init = new Initializer();
		String vtables =  init.initVTables();
		
		
		StringBuilder sb = new StringBuilder();		
		sb.append(getRunFunction());
		StringBuilder sb2 = new StringBuilder();
		
		sb2.append("#include \"cubex_lib.h\"\n");
		sb2.append("#include \"cubex_main.h\"\n");
		sb2.append("#include \"cubex_external_functions.h\"\n\n");
		sb2.append(GlobalAwareness.getDeclarations());
		sb2.append("object_t *v_input;\n");
		for(String var : GlobalAwareness.locals.keySet())
		{
			sb2.append("\tobject_t * ").append(CUtils.canonName(var)).append(" = NULL;\n");
		}
		

		sb2.append(vtables).append(init.init());
		sb2.append(sb);
		return sb2.toString();
	}
	
	public String getRunFunction() throws ContextException
	{
		StringBuilder sb = new StringBuilder();
		
		for(CubeXProgramPiece piece : pieces){
			if(piece.isFunction())
			{
				sb.append(piece.preC());
				sb.append(piece.toC());
			}
		}
		
		for(CubeXProgramPiece piece : pieces){
			if(piece.isClass()||piece.isInterface())
			{
				if(piece.isClass())
					GlobalAwareness.addClass((CubeXClass)piece);
				sb.append(piece.preC());
				sb.append(piece.toC());
			}
		}
		
		
		sb.append("\n");
		sb.append("object_t * cubex_main_int()\n{\n");


		StringBuilder sbafter = new StringBuilder();
		
		for(CubeXProgramPiece piece : pieces){
			if(piece.isStatement())
			{
				String pre = piece.preC();
				if(!pre.isEmpty())
					sbafter.append("\t").append(pre);
				
				sbafter.append("\t").append(piece.toC());
			}
		}
		sbafter.append("\n}\n");
		
		sb.append("\tv_input=getInput();\n");
		sb.append("\tinit_VTables();\n\n");
		sb.append(sbafter.toString());
		
		return sb.toString();
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
	
	public ArrayList<CubeXProgramPiece> initCFG()
	{
		ArrayList<CubeXProgramPiece> returns = new ArrayList<>();

		if(pieces.size()==0)
		{
			return returns;
		}
				
		CubeXProgramPiece cur = pieces.get(0);
		for(int i=0; i<pieces.size()-1; ++i)
		{
			CubeXProgramPiece next = pieces.get(i+1);
			returns.addAll(cur.initializeSucc(next, true));
			cur=next;
		}
		returns.addAll(cur.initializeSucc(null, true));
		cur.setTopLevel(true);
		return returns;
	}
	
	public void updateDeadVariables()
	{
		for(CubeXProgramPiece piece : pieces)
		{
			piece.updateDeadVariables();
		}
	}
	
	public void flattenPieces(){
		boolean notFlattened = true;
		while(notFlattened){
			notFlattened = false;
			ArrayList<CubeXProgramPiece> flattenedPieces = new ArrayList<>();
			for(CubeXProgramPiece piece : pieces){
				CubeXProgramPiece flattened = piece.flatten();
				if(!flattened.equals(piece)){
					notFlattened = true;
				}
				if(flattened.isStatement() && ((CubeXStatement)flattened).isBlock()){
					flattenedPieces.addAll(((CubeXBlock)flattened).getInnerStatements());
				}
				else{
					flattenedPieces.add(flattened);
				}
			}
			pieces = flattenedPieces;
		}
		
	}
	
	public void addBoxes()
	{
		for(CubeXProgramPiece piece : pieces)
		{
			piece.addBoxes();
		}
	}
	
	public void simplifyFunctionBoxes()
	{
		for(CubeXProgramPiece piece : pieces)
		{
			piece.simplifyFunctionBoxes();
		}
	}
	
	public void primitivifyVariables()
	{
		for(CubeXProgramPiece piece : pieces)
		{
			piece.primitivifyVariables();
		}
	}
	
	public void reduceBoxes()
	{
		for(CubeXProgramPiece piece : pieces)
		{
			piece.reduceBoxes();
		}
	}
	
	public void eliminateCommonSubexpressions() throws ContextException{
		ExpressionContext con = new ExpressionContext(null);
		con.add(new CubeXVariable("input"), new CubeXVariable("input"));
		
		for(int i = 0; i < pieces.size();i++){
			con = pieces.get(i).eliminateCommonSubexpressions(con);
		}
	}
}
