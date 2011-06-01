package com.jayway.changeless.utilities;

import com.jayway.changeless.functions.Fn2;

public final class Comparables {
	private Comparables() { /* Static class */ }
	
	/**
	 * Returns true if the left value is less than the right value.
	 * @param <T> The type of the values to compare.
	 * @param left The left value.
	 * @param rigth The right value.
	 * @return true if the left value is less than the right value; false otherwise.
	 */
	public static <T extends Comparable<T>> boolean lessThan(T left, T rigth) {
		return left.compareTo(rigth) < 0;
	}
	
	/**
	 * Returns true if the left value is greater than the right value.
	 * @param <T> The type of the values to compare.
	 * @param left The left value.
	 * @param rigth The right value.
	 * @return true if the left value is greater than the right value; false otherwise.
	 */
	public static <T extends Comparable<T>> boolean greaterThan(T left, T rigth) {
		return left.compareTo(rigth) > 0;
	}
	
	/**
	 * Returns true if the left value is greater than or equals to the right value.
	 * @param <T> The type of the values to compare.
	 * @param left The left value.
	 * @param rigth The right value.
	 * @return true if the left value is greater than or equals to the right value; false otherwise.
	 */
	public static <T extends Comparable<T>> boolean greaterThanOrEquals(T left, T rigth) {
		return left.compareTo(rigth) >= 0;
	}
	
	/**
	 * Returns true if the left value is less than or equals to the right value.
	 * @param <T> The type of the values to compare.
	 * @param left The left value.
	 * @param rigth The right value.
	 * @return true if the left value is less than or equals to the right value; false otherwise.
	 */
	public static <T extends Comparable<T>> boolean lessThanOrEquals(T left, T rigth) {
		return left.compareTo(rigth) <= 0;
	}
	
	/**
	 * Returns true if the left value is equals to the right value.
	 * @param <T> The type of the values to compare.
	 * @param left The left value.
	 * @param rigth The right value.
	 * @return true if the left value is equals to the right value; false otherwise.
	 */
	public static <T extends Comparable<T>> boolean equals(T left, T rigth) {
		return left.compareTo(rigth) == 0;
	}
	
	/**
	 * Returns a minimum function for elements of type T.
	 * @param <T> the type of the elements this minimum function can compare.
	 * @return a minimum function for elements of type T.
	 */
	public static <T extends Comparable<T>> Fn2<T,T,T> minFunction() {
		return new Fn2<T,T,T>() {
			@Override
			public T apply(T x, T y) {
				return Comparables.lessThan(x, y) ? x : y;
			}
		};
	}
	
	/**
	 * Returns a maximum function for elements of type T.
	 * @param <T> the type of the elements this maximum function can compare.
	 * @return a maximum function for elements of type T.
	 */
	public static <T extends Comparable<T>> Fn2<T,T,T> maxFunction() {
		return new Fn2<T,T,T>() {
			@Override
			public T apply(T x, T y) {
				return Comparables.greaterThan(x, y) ? x : y;
			}
		};
	}
}
