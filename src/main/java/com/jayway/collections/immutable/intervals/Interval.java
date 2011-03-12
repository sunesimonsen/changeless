package com.jayway.collections.immutable.intervals;

public interface Interval<T extends Comparable<T>> {
	boolean Contains(T value);
}
