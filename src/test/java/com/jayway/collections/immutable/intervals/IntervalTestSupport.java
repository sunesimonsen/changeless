package com.jayway.collections.immutable.intervals;

import static com.jayway.collections.immutable.intervals.IntervalAssert.assertContains;
import static com.jayway.collections.immutable.intervals.IntervalAssert.assertOutsideInterval;

import org.junit.Test;

public abstract class IntervalTestSupport<T extends Comparable<T>> {
	protected abstract Interval<T> interval();
	protected abstract T valueBefore();
	protected abstract T valueAfter();
	protected abstract T valueAtStart();
	protected abstract T valueAtEnd();
	protected abstract T valueInInterval();
	
	@Test
	public void valuesBeforeIsNotIncluded() throws Exception {
		assertOutsideInterval(interval(), valueBefore());
	}
	
	@Test
	public void valuesAfterIsNotIncluded() throws Exception {
		assertOutsideInterval(interval(), valueAfter());
	}
	
	@Test
	public void valuesAtStartOfIntervalIsIncluded() throws Exception {
		assertContains(interval(), valueAtStart());
	}
	
	@Test
	public void valuesAtEndOfIntervalIsIncluded() throws Exception {
		assertContains(interval(), valueAtEnd());
	}
	
	@Test
	public void valuesAtMiddleOfIntervalIsIncluded() throws Exception {
		assertContains(interval(), valueInInterval());
	}
}
