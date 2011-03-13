package com.jayway.collections.immutable.intervals;

import com.jayway.collections.utilities.Comparables;

public class RightOpenInterval<T extends Comparable<T>> extends
		FiniteIntervalSupport<T> {

	public RightOpenInterval(T start, T end) {
		super(start, end);
	}

	@Override
	public boolean contains(T value) {
		return Comparables.lessThanOrEquals(getStart(), value)
				&& Comparables.LessThan(value, getEnd());
	}

	@Override
	public String toString() {
		return String.format("[%s,%s)", getStart(), getEnd());
	}

}
