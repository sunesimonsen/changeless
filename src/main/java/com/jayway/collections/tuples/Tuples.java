package com.jayway.collections.tuples;

public final class Tuples {
	private Tuples() {}
	
	public static <T1, T2> Tuple<T1, T2> of(T1 first, T2 second) {
		return new DefaultTuple<T1, T2>(first, second); 
	}
}
