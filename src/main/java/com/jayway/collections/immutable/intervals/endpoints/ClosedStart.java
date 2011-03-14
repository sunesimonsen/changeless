package com.jayway.collections.immutable.intervals.endpoints;

import com.jayway.collections.utilities.Comparables;

public final class ClosedStart<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalStart<T> {
	public ClosedStart(T value) {
		super(value);
	}

	@Override
	public boolean after(T value) {
		return Comparables.greaterThanOrEquals(value, getValue());
	}
	
	@Override
	public String toString() {
		return "[" + getValue();
	}
}
