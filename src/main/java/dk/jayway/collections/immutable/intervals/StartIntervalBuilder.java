package dk.jayway.collections.immutable.intervals;

public class StartIntervalBuilder<T extends Comparable<T>> {
	private final T start;

	public StartIntervalBuilder(T start) {
		this.start = start;
	}
	
	public Interval<T> close(T end) {
		return new ClosedInterval<T>(start, end);
	}

	public Interval<T> open(T end) {
		return new RightOpenInterval<T>(start,end);
	}
}
