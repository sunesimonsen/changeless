package com.jayway.changeless.intervals;

import com.jayway.changeless.predicates.Predicate;

/**
 * An interval that can decide if values are contained.
 * @param <T> The type of the elements in the interval.
 */
public interface Interval<T extends Comparable<T>> extends Predicate<T> {
	/**
	 * Returns true if the given value is contained by this interval; false otherwise.
	 * @param value the value to check.
	 * @return true if the given value is contained by this interval; false otherwise.
	 */
	boolean contains(T value);
}
