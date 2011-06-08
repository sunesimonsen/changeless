package com.jayway.changeless.tuples;

import com.jayway.changeless.functions.Fn;

/**
 * A utility class for working with {@link Tuple}'s.
 */
public final class Tuples {
	private Tuples() { /* Static class */ }
	
	/**
	 * Create a new tuple with the given values.
	 * @param <T1> the type of the first component of the tuple.
	 * @param <T2> the type of the second component of the tuple.
	 * @param first the first component of the tuple.
	 * @param second the second component of the tuple.
	 * @return the created tuple.
	 */
	public static <T1, T2> Tuple<T1, T2> of(T1 first, T2 second) {
		return new DefaultTuple<T1, T2>(first, second); 
	}
	
	/**
	 * A function that extract the first component of a tuple.
	 * @param <T1> the type of the first component of the tuple.
	 * @param <T2> the type of the second component of the tuple.
	 * @return the function.
	 */
	public static <T1,T2> Fn<Tuple<T1,T2>, T1> firstFunction() {
		return new Fn<Tuple<T1,T2>, T1>() {
			@Override
			public T1 apply(Tuple<T1, T2> input) {
				return input.getFirst();
			}
		};
	}
	
	/**
	 * A function that extract the second component of a tuple.
	 * @param <T1> the type of the first component of the tuple.
	 * @param <T2> the type of the second component of the tuple.
	 * @return the function.
	 */
	public static <T1,T2> Fn<Tuple<T1,T2>, T2> secondFunction() {
		return new Fn<Tuple<T1,T2>, T2>() {
			@Override
			public T2 apply(Tuple<T1, T2> input) {
				return input.getSecond();
			}
		};
	}
}
