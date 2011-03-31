package com.jayway.changeless.immutable.intervals.endpoints;

public final class InfiniteEnd<T extends Comparable<T>> implements IntervalEnd<T> {

	@Override
	public boolean before(T value) {
		return true;
	}
	
	@Override
	public String toString() {
		return "∞)";
	}
}
