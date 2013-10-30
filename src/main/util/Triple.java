package main.util;

public class Triple<P,Q,R> {

	public P first;
	public Q second;
	public R third;
	
	public Triple(P p, Q q, R r)
	{
		first=p;
		second=q;
		third=r;
	}
	
	public Triple(P p, Q q)
	{
		first=p;
		second=q;
		third=null;
	}

}
