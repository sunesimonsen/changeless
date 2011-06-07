package com.jayway.changeless.intervals;


/**
 * A utility class for working with {@link Interval} classes.
 */
public final class Intervals {
	private Intervals() { /* Static class */ }
	
	/**
	 * A builder for building integer intervals starting from zero including zero.
	 */
	public static StartIntervalBuilder<Integer> zero = closed(0);
	
	/**
	 * Creates a new interval builder starting from and including the given value.
	 * @param <T> the type of the values in the intervals being build.
	 * @param start The starting point for the interval to build.
	 * @return the interval builder.
	 */
	public static <T extends Comparable<T>> StartIntervalBuilder<T> closed(T start) {
		return new StartIntervalBuilder<T>(new ClosedStart<T>(start));
	}

	/**
	 * Creates a new interval builder starting from but not including the given value.
	 * @param <T> the type of the values in the intervals being build.
	 * @param start The starting point for the interval to build.
	 * @return the interval builder.
	 */
	public static <T extends Comparable<T>> StartIntervalBuilder<T> open(T start) {
		return new StartIntervalBuilder<T>(new OpenStart<T>(start));
	}

	/**
	 * Creates a new interval builder starting the open negative infinite interval.
	 * @param <T> the type of the values in the intervals being build.
	 * @return the interval builder.
	 */
	public static <T extends Comparable<T>> StartIntervalBuilder<T> infinite() {
		return new StartIntervalBuilder<T>(new InfiniteStart<T>());
	}
}
