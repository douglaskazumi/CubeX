package main.context;

import java.util.HashMap;

import main.exceptions.ContextException;

public class BaseContext<T> {

	protected BaseContext<T> parent;
	protected HashMap<String, T> context;
	private boolean isMutable = true;
	
	public BaseContext(BaseContext<T> parent) 
	{
		context=new HashMap<String, T>();
		this.parent=parent;
	}
	
	public T lookup(String id)
	{
		if(context.containsKey(id))
		{
			return context.get(id);
		}
		else
		{
			if(parent!=null)
			{
				return parent.lookup(id);
			}
			else
			{
				return null;
			}
		}
	}

	public void add(String id, T item) throws ContextException
	{
		if(id==null)
			throw new ContextException();
		T value = lookup(id);
		if(value==null)
		{
			context.put(id, item);
		}
		else
		{
			if(isMutable)
			{
				if(context.containsKey(id))
				{
					context.put(id, item);
				}
				else
				{
					parent.add(id, item);
				}
			}
			else
			{
				throw new ContextException("Trying to set immutable variable");
			}
		
		}
		
	}
	
	public BaseContext<T> createChildContext()
	{
		return new BaseContext<T>(this);
	}

	public boolean isMutable() {
		return isMutable;
	}

	public void setMutable(boolean isMut)
	{
		isMutable=isMut;
	}

}
