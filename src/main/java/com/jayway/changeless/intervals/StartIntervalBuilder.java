package com.jayway.changeless.intervals;

/**
 * A builder capable of building {@link Interval} instances.
 * 
 * @param <T> the type of the values in the intervals being build.
 */
public final class StartIntervalBuilder<T extends Comparable<T>> {
	private final IntervalStart<T> start;

	StartIntervalBuilder(IntervalStart<T> start) {
		this.start = start;
	}
	
	/**
	 * Create an interval that ends with and includes the given value.
	 * @param end the value that the interval should end in.
	 * @return the created interval.
	 */
	public Interval<T> closed(T end) {
		return new DefaultInterval<T>(start, new ClosedEnd<T>(end));
	}

	/**
	 * Create an interval that ends with but does include the given value.
	 * @param end the value that the interval should end in.
	 * @return the created interval.
	 */
	public Interval<T> open(T end) {
		return new DefaultInterval<T>(start, new OpenEnd<T>(end));
	}
	
	/**
	 * Create an interval that ends in infinite.
	 * @return the created interval.
	 */
	public Interval<T> infinite() {
		return new DefaultInterval<T>(start, new InfiniteEnd<T>());
	}
}
