package com.jayway.collections.immutable.intervals;


public abstract class FiniteIntervalSupport<T extends Comparable<T>> implements Interval<T> {
	private final T start;
	private final T end;

	public FiniteIntervalSupport(T start, T end) {
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return start;
	}

	public T getEnd() {
		return end;
	}
}
