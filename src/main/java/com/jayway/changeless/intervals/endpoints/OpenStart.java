package com.jayway.changeless.intervals.endpoints;

import com.jayway.changeless.utilities.Comparables;

public final class OpenStart<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalStart<T> {
	public OpenStart(T value) {
		super(value);
	}

	@Override
	public boolean after(T value) {
		return Comparables.greaterThan(value,getValue());
	}
	
	@Override
	public String toString() {
		return "(" + getValue();
	}
}
