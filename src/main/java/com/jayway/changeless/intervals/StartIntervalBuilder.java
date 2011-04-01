package com.jayway.changeless.intervals;


public final class StartIntervalBuilder<T extends Comparable<T>> {
	private final IntervalStart<T> start;

	public StartIntervalBuilder(IntervalStart<T> start) {
		this.start = start;
	}
	
	public Interval<T> closed(T end) {
		return new DefaultInterval<T>(start, new ClosedEnd<T>(end));
	}

	public Interval<T> open(T end) {
		return new DefaultInterval<T>(start, new OpenEnd<T>(end));
	}
	
	public Interval<T> infinite() {
		return new DefaultInterval<T>(start, new InfiniteEnd<T>());
	}
}
