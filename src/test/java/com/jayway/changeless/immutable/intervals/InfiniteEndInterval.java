package com.jayway.changeless.immutable.intervals;

import static com.jayway.changeless.immutable.intervals.IntervalAssert.assertContains;

import org.junit.Test;

import com.jayway.changeless.immutable.intervals.Interval;
import com.jayway.changeless.immutable.intervals.Intervals;

public class InfiniteEndInterval {
	private Interval<Integer> interval = Intervals.closed(4).infinite();
	
	@Test
	public void maximumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MAX_VALUE);
	}
}
