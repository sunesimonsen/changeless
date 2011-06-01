package com.jayway.changeless.intervals;

final class InfiniteEnd<T extends Comparable<T>> implements IntervalEnd<T> {

	@Override
	public boolean before(T value) {
		return true;
	}
	
	@Override
	public String toString() {
		return "∞)";
	}
}
