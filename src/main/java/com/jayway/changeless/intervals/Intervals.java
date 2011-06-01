package com.jayway.changeless.intervals;


public final class Intervals {
	private Intervals() { /* Static class */ }
	
	public static StartIntervalBuilder<Integer> zero = closed(0);
	
	public static <T extends Comparable<T>> StartIntervalBuilder<T> closed(T start) {
		return new StartIntervalBuilder<T>(new ClosedStart<T>(start));
	}

	public static <T extends Comparable<T>> StartIntervalBuilder<T> open(T start) {
		return new StartIntervalBuilder<T>(new OpenStart<T>(start));
	}

	public static <T extends Comparable<T>> StartIntervalBuilder<T> infinite() {
		return new StartIntervalBuilder<T>(new InfiniteStart<T>());
	}
}
