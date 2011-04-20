package com.jayway.changeless.functions.objects;

import com.jayway.changeless.functions.Fn;

/**
 * The function that returns the result of calling {@link Object#toString()} on it's input argument.
 */
public class ToStringFunction  implements Fn<Object, String> {
	/**
	 * Calls {@link Object#toString()} on the given input object and returns the computed string.
	 * @param input the input that this function should be applied on.
	 * @return the string representation of the given input object.
	 */
	@Override
	public String apply(Object input) {
		return input.toString();
	}
}