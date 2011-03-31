package com.jayway.changeless.utilities;

public final class Comparables {
	private Comparables() {}
	
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
}
