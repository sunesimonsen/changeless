package com.jayway.changeless.intervals;


final class DefaultInterval<T extends Comparable<T>> implements Interval<T> {
	private final IntervalStart<T> start;
	private final IntervalEnd<T> end;

	public DefaultInterval(IntervalStart<T> start, IntervalEnd<T> end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean contains(T value) {
		return start.after(value) && end.before(value);
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s", start, end);
	}

	@Override
	public boolean matches(T input) {
		return contains(input);
	}
}
