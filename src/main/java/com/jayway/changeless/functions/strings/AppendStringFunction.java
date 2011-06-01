package com.jayway.changeless.functions.strings;

import com.jayway.changeless.functions.Fn2;

/**
 * A function that calls {@link Object#toString()} on the input argument and appends it to the result 
 * {@link StringBuilder}.
 */
public class AppendStringFunction implements Fn2<StringBuilder, Object, StringBuilder> {
	/**
	 * Calls toString on the input argument and appends it to the result 
	 * StringBuilder.
	 * @param result the {@link StringBuilder} that holds the string representation of the 
	 * given input should be appended to.
	 * @param input the input which string representation should be appended to the 
	 * result {@link StringBuilder} 
	 */
	@Override
	public StringBuilder apply(StringBuilder result, Object input) {
		return result.append(input);
	}
}
