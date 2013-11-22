package main.Optimizations;

import java.util.ArrayList;

import main.context.BaseContext;
import main.exceptions.ContextException;
import main.expression.CubeXExpression;
import main.expression.CubeXVariable;

public class ExpressionContext extends BaseContext<CubeXVariable, CubeXExpression> {

	public ExpressionContext(ExpressionContext p) {
		super(p);
	}

	public ExpressionContext createChildContext()
	{
		return new ExpressionContext(this);
	}
	
	public int getSize(){
		return context.size() + (parent == null ? 0 :((ExpressionContext)parent).getSize());
	}
	
	public ArrayList<CubeXVariable> getAllVariables(){
		ArrayList<CubeXVariable> all = new ArrayList<CubeXVariable>();
		all.addAll(context.keySet());
		if(parent != null)
			all.addAll(((ExpressionContext) parent).getAllVariables());
		return all;
	}
	
	public ArrayList<CubeXExpression> getAllExpressions(){
		ArrayList<CubeXExpression> all = new ArrayList<CubeXExpression>();
		all.addAll(context.values());
		if(parent != null)
			all.addAll(((ExpressionContext) parent).getAllExpressions());
		return all;
	}
	
	public CubeXVariable getVariableHolding(CubeXExpression expr,CubeXVariable var){
		if(context.containsValue(expr)){
			for(CubeXVariable key : getAllVariables()){
				if(expr.equals(lookup(key)) && !key.equals(var)){
					return key;
				}
			}
		}

		return null;
	}
	
	public void merge(ExpressionContext local, ExpressionContext other) throws ContextException{
		int thisSize = getSize();
		int otherSize = getSize();
		ArrayList<CubeXVariable> big = getAllVariables();
		ExpressionContext biggerCon = this;
		ExpressionContext smallerCon = other;
		
		//Iterate based on the bigger set
		if(otherSize > thisSize){
			big = other.getAllVariables();
			biggerCon = other;
			smallerCon = this;
		}

		for(CubeXVariable varBig : big){
			CubeXExpression fromBig = biggerCon.lookup(varBig);
			CubeXExpression fromSmall = smallerCon.lookup(varBig);
			if(fromBig.equals(fromSmall)){
				local.add(varBig, fromBig);
			}
			else{
				local.add(varBig, varBig);
			}
		}
	}
}
