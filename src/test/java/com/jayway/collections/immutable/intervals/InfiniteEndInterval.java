package com.jayway.collections.immutable.intervals;

import static com.jayway.collections.immutable.intervals.IntervalAssert.assertContains;

import org.junit.Test;

public class InfiniteEndInterval {
	private Interval<Integer> interval = Intervals.closed(4).infinite();
	
	@Test
	public void maximumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MAX_VALUE);
	}
}
