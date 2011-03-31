package com.jayway.changeless.immutable.intervals;

import com.jayway.changeless.predicates.Predicate;

public interface Interval<T extends Comparable<T>> extends Predicate<T> {
	boolean contains(T value);
}
