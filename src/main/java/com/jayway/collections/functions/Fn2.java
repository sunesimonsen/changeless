package com.jayway.collections.functions;

/**
 * Interface for functions that takes two arguments.
 *
 * @param <T1> The type of the first input.
 * @param <T2> The type of the second input.
 * @param <R> The type of the result.
 */
public interface Fn2<T1,T2,R> {
	/**
	 * Applies the function to the given arguments.
	 * @param x the first input.
	 * @param y the second input.
	 * @return the return values.
	 */
	public R apply(T1 x, T2 y);
}
