package com.jayway.changeless.intervals.endpoints;

import com.jayway.changeless.utilities.Comparables;

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
