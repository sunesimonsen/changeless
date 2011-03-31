package com.jayway.changeless.immutable.intervals.endpoints;

import com.jayway.changeless.utilities.Comparables;


public final class OpenEnd<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalEnd<T> {
	public OpenEnd(T value) {
		super(value);
	}

	@Override
	public boolean before(T value) {
		return Comparables.lessThan(value, getValue());
	}
	
	@Override
	public String toString() {
		return getValue() + ")";
	}
}
