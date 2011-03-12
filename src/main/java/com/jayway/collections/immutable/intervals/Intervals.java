package com.jayway.collections.immutable.intervals;

public final class Intervals {
	private Intervals() {}
	
	public static <T extends Comparable<T>> StartIntervalBuilder<T> close(T start) {
		return new StartIntervalBuilder<T>(start);
	}
}
