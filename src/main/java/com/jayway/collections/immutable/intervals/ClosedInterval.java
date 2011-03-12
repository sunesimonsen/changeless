package com.jayway.collections.immutable.intervals;

import com.jayway.collections.utilities.Comparables;

public class ClosedInterval<T extends Comparable<T>> extends
		FiniteIntervalSupport<T> {
	public ClosedInterval(T start, T end) {
		super(start, end);
	}

	@Override
	public boolean Contains(T value) {
		return Comparables.LessThanOrEquals(getStart(), value)
				&& Comparables.LessThanOrEquals(value, getEnd());
	}
	
	@Override
	public String toString() {
		return String.format("[%s,%s]", getStart(), getEnd());
	}
}
