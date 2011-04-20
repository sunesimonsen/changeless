package com.jayway.changeless.functions;

/**
 * Interface for functions that takes one arguments.
 * 
 * @param <T> the type of the parameter.
 * @param <R> the type of the return value.
 */
public interface Fn<T,R> {
	/**
	 * Apply this function to the given argument.
	 * @param input that this function should be applied on.
	 * @return the return value of this function.
	 */
	R apply(T input);
}
