package com.jayway.changeless.intervals.endpoints;

public interface IntervalEnd<T extends Comparable<T>>  {
	/**
	 * Returns true if the given value is less than end-point.
	 * @param value The value to be checked.
	 * @return true if the given value is less than end-point; false otherwise.
	 */
	boolean before(T value);
}
