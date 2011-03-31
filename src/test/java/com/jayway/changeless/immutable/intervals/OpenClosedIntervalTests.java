package com.jayway.changeless.immutable.intervals;

import com.jayway.changeless.immutable.intervals.Interval;
import com.jayway.changeless.immutable.intervals.Intervals;


public class OpenClosedIntervalTests extends IntervalTestSupport<Integer>{

	@Override
	protected Interval<Integer> interval() {
		return Intervals.closed(1).closed(4);
	}

	@Override
	protected Integer valueAfter() {
		return 5;
	}

	@Override
	protected Integer valueAtEnd() {
		return 4;
	}

	@Override
	protected Integer valueAtStart() {
		return 1;
	}

	@Override
	protected Integer valueBefore() {
		return 0;
	}

	@Override
	protected Integer valueInInterval() {
		return 2;
	}
}
