package com.jayway.changeless.tuples;

import com.jayway.changeless.functions.Fn;

public final class Tuples {
	private Tuples() {}
	
	public static <T1, T2> Tuple<T1, T2> of(T1 first, T2 second) {
		return new DefaultTuple<T1, T2>(first, second); 
	}
	
	public static <T1,T2> Fn<Tuple<T1,T2>, T1> firstFunction() {
		return new Fn<Tuple<T1,T2>, T1>() {
			@Override
			public T1 apply(Tuple<T1, T2> input) {
				return input.getFirst();
			}
		};
	}
	
	public static <T1,T2> Fn<Tuple<T1,T2>, T2> secondFunction() {
		return new Fn<Tuple<T1,T2>, T2>() {
			@Override
			public T2 apply(Tuple<T1, T2> input) {
				return input.getSecond();
			}
		};
	}
}
