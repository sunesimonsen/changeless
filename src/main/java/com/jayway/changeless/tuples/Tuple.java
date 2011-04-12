package com.jayway.changeless.tuples;

/**
 * A tuple is an ordered list of elements. 
 * This is the the tuple of two elements.
 * @param <T1> the type of the first element.
 * @param <T2> the type of the second element.
 */
public interface Tuple<T1, T2> {
	/**
	 * Returns the first element of this tuple.
	 * @return the first element of this tuple.
	 */
	public T1 getFirst();
	
	/**
	 * Returns the second element of this tuple.
	 * @return the second element of this tuple.
	 */
	public T2 getSecond();
}
