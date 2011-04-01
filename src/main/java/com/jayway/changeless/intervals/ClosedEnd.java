package com.jayway.changeless.intervals;

import com.jayway.changeless.utilities.Comparables;

final class ClosedEnd<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalEnd<T> {
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
