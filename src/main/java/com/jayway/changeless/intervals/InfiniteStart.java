package com.jayway.changeless.intervals;

final class InfiniteStart<T extends Comparable<T>> implements IntervalStart<T> {

	@Override
	public boolean after(T value) {
		return true;
	}
	
	@Override
	public String toString() {
		return "(∞";
	}
}
