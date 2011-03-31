package com.jayway.changeless.immutable.intervals;

import static com.jayway.changeless.immutable.intervals.IntervalAssert.assertContains;

import org.junit.Test;

import com.jayway.changeless.immutable.intervals.Interval;
import com.jayway.changeless.immutable.intervals.Intervals;

public class InfiniteStartInterval {
	private Interval<Integer> interval = Intervals.<Integer>infinite().closed(4);
	
	@Test
	public void minimumValueIsIncluded() throws Exception {
		assertContains(interval, Integer.MIN_VALUE);
	}
}
