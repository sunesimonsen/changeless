package com.jayway.changeless.functions;

import com.jayway.changeless.functions.objects.ToStringFunction;

/**
 * A class containing convenient functions for working with function-objects.
 */
public final class Functions {
	private Functions() { /* Static class */ }
	
	/**
	 * The function that returns the result of calling toString on it's input argument.
	 */
	public static Fn<Object, String> toStringFunction = new ToStringFunction();
}
