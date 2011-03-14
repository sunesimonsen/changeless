package com.jayway.collections.immutable.intervals.endpoints;

import com.jayway.collections.utilities.Comparables;

public final class ClosedEnd<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalEnd<T> {
	public ClosedEnd(T value) {
		super(value);
	}

	@Override
	public boolean before(T value) {
		return Comparables.lessThanOrEquals(value, getValue());
	}
	
	@Override
	public String toString() {
		return getValue() + "]";
	}
}
