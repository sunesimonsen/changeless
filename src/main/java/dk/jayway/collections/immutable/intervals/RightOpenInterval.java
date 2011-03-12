package dk.jayway.collections.immutable.intervals;

import dk.jayway.collections.utilities.Comparables;

public class RightOpenInterval<T extends Comparable<T>> extends
		FiniteIntervalSupport<T> {

	public RightOpenInterval(T start, T end) {
		super(start, end);
	}

	@Override
	public boolean Contains(T value) {
		return Comparables.LessThanOrEquals(getStart(), value)
				&& Comparables.LessThan(value, getEnd());
	}

	@Override
	public String toString() {
		return String.format("[%s,%s)", getStart(), getEnd());
	}

}
