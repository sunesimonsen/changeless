package com.jayway.collections.functions;

import com.jayway.collections.utilities.Comparables;

public final class Functions {
	private Functions() {}
	
	public static Fn<Object, String> toStringFunction = new Fn<Object, String>(){
		@Override
		public String apply(Object input) {
			return input.toString();
		}
	};
	
	public static Fn2<Integer, Integer, Integer> plusFunction = 
		new Fn2<Integer, Integer, Integer>() {
		@Override
		public Integer apply(Integer input, Integer result) {
			return input + result;
		}
	};
	
	public static Fn2<StringBuilder, Object, StringBuilder> appendStringFunction =
		new Fn2<StringBuilder, Object, StringBuilder>() {
			@Override
			public StringBuilder apply(StringBuilder result, Object input) {
				return result.append(input);
			}
		};
		
	public static <T extends Comparable<T>> Fn2<T,T,T> minFunction() {
		return new Fn2<T,T,T>() {
			@Override
			public T apply(T x, T y) {
				return Comparables.lessThan(x, y) ? x : y;
			}
		};
	}
		
	public static <T extends Comparable<T>> Fn2<T,T,T> maxFunction() {
		return new Fn2<T,T,T>() {
			@Override
			public T apply(T x, T y) {
				return Comparables.greaterThan(x, y) ? x : y;
			}
		};
	}
}
