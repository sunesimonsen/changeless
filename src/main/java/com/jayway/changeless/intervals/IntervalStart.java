package com.jayway.changeless.intervals;

interface IntervalStart<T extends Comparable<T>> {
	/**
	 * Returns true if the given value is greater than start-point.
	 * @param value The value to be checked.
	 * @return true if the given value is greater than start-point; false otherwise.
	 */
	boolean after(T value);
}
