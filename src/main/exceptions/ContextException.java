package main.exceptions;

public class ContextException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8633387361616341178L;

	public ContextException() {
	}

	public ContextException(String arg0) {
		super(arg0);
	}

	public ContextException(Throwable arg0) {
		super(arg0);
	}

	public ContextException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ContextException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
