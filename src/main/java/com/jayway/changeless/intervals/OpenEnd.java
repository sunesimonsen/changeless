package com.jayway.changeless.intervals;

import com.jayway.changeless.utilities.Comparables;


final class OpenEnd<T extends Comparable<T>> extends FiniteEndpoint<T> implements IntervalEnd<T> {
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
