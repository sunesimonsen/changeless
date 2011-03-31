package com.jayway.changeless.immutable.intervals;

import com.jayway.changeless.immutable.intervals.endpoints.ClosedStart;
import com.jayway.changeless.immutable.intervals.endpoints.InfiniteStart;
import com.jayway.changeless.immutable.intervals.endpoints.OpenStart;

public final class Intervals {
	private Intervals() {}
	
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
