package com.jayway.collections.immutable.intervals;

import com.jayway.collections.predicates.Predicate;

public interface Interval<T extends Comparable<T>> extends Predicate<T> {
	boolean contains(T value);
}
