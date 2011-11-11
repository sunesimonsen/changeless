package com.jayway.changeless.intervals;

import static com.jayway.changeless.intervals.IntervalAssert.assertContains;

import org.junit.Test;

import com.jayway.changeless.intervals.Interval;
import com.jayway.changeless.intervals.Intervals;

public class InfiniteEndIntervalTest {
	private Interval<Integer> interval = Intervals.closed(4).infinite();
	
	@Test
	public void maximumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MAX_VALUE);
	}
}
