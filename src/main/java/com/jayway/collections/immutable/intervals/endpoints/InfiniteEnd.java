package com.jayway.collections.immutable.intervals.endpoints;

public class InfiniteEnd<T extends Comparable<T>> implements IntervalEnd<T> {

	@Override
	public boolean before(T value) {
		return true;
	}
	
	@Override
	public String toString() {
		return "∞)";
	}
}
