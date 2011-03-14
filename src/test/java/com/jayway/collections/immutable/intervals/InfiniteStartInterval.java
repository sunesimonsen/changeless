package com.jayway.collections.immutable.intervals;

import static com.jayway.collections.immutable.intervals.IntervalAssert.assertContains;

import org.junit.Test;

public class InfiniteStartInterval {
	private Interval<Integer> interval = Intervals.<Integer>infinite().closed(4);
	
	@Test
	public void minimumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MIN_VALUE);
	}
}
