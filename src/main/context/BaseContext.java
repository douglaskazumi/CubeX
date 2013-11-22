package main.context;

import java.util.HashMap;

import main.exceptions.ContextException;

public class BaseContext<S,T> {

	protected BaseContext<S,T> parent;
	protected HashMap<S, T> context;
	private boolean isMutable = true;
	
	public BaseContext(BaseContext<S,T> parent) 
	{
		context=new HashMap<S, T>();
		this.parent=parent;
	}
	
	public T lookup(S id)
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

	public void add(S id, T item) throws ContextException
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
	
	public BaseContext<S,T> createChildContext()
	{
		return new BaseContext<S,T>(this);
	}

	public boolean isMutable() {
		return isMutable;
	}

	public void setMutable(boolean isMut)
	{
		isMutable=isMut;
	}

}
