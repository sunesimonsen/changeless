package com.jayway.changeless.intervals;

import static com.jayway.changeless.intervals.IntervalAssert.assertContains;

import org.junit.Test;

import com.jayway.changeless.intervals.Interval;
import com.jayway.changeless.intervals.Intervals;

public class InfiniteStartIntervalTest {
	private Interval<Integer> interval = Intervals.<Integer>infinite().closed(4);
	
	@Test
	public void minimumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MIN_VALUE);
	}
}
