package com.jayway.changeless.intervals;

import com.jayway.changeless.intervals.Interval;
import com.jayway.changeless.intervals.Intervals;


public class ClosedOpenIntervalTests extends IntervalTestSupport<Integer>{

	@Override
	protected Interval<Integer> interval() {
		return Intervals.open(1).open(4);
	}

	@Override
	protected Integer valueAfter() {
		return 4;
	}

	@Override
	protected Integer valueAtEnd() {
		return 3;
	}

	@Override
	protected Integer valueAtStart() {
		return 2;
	}

	@Override
	protected Integer valueBefore() {
		return 1;
	}

	@Override
	protected Integer valueInInterval() {
		return 3;
	}
}
