package com.jayway.changeless.intervals;

import com.jayway.changeless.intervals.Interval;
import com.jayway.changeless.intervals.Intervals;


public class OpenOpenIntervalTests extends IntervalTestSupport<Integer>{

	@Override
	protected Interval<Integer> interval() {
		return Intervals.closed(1).open(4);
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
