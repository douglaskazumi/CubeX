package main.context;

import java.util.HashMap;

import main.exceptions.ContextException;

public class BaseContext<T> {

	protected BaseContext<T> parent;
	private HashMap<String, T> context;
	private boolean isMutable = false;
	
	public BaseContext(BaseContext<T> parent) 
	{
		context=new HashMap<String, T>();
		this.parent=parent;
	}
	
	public void add(String id, T item) throws ContextException
	{
		if(id==null)
			throw new ContextException();
		T value = internalLookup(id);
		if(value==null)
		{
			context.put(id, item);
		}
		else
		{
			if(isMutable && context.containsKey(id))
			{
				context.put(id, item);
			}
			else
			{
				throw new ContextException("Trying to set immutable variable");
			}
		}
		
	}
	
	public void setMutable(boolean isMut)
	{
		isMutable=isMut;
	}
	
	public BaseContext<T> createChildContext()
	{
		return new BaseContext<T>(this);
	}
	
	public T lookup(String id) throws ContextException
	{
		T value = internalLookup(id);
		if(value==null)
			throw new ContextException("Bad Id = "+id);
		return value;
	}
	
	private T internalLookup(String id) throws ContextException
	{
		if(context.containsKey(id))
		{
			return context.get(id);
		}
		else
		{
			if(parent!=null)
			{
				return parent.internalLookup(id);
			}
			else
			{
				return null;
			}
		}
	}

}
