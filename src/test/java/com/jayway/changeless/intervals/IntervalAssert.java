package com.jayway.changeless.intervals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.jayway.changeless.intervals.Interval;

public class IntervalAssert {
	public static <T extends Comparable<T>> void assertOutsideInterval(Interval<T> interval, T value) {
		String message = String.format("Expect value '%s' to be outside interval %s", value, interval);
		assertFalse(message, interval.contains(value));
	}

	public static <T extends Comparable<T>> void assertContains(Interval<T> interval, T value) {
		String message = String.format("Expect value '%s' to be contained in interval %s", value, interval);
		assertTrue(message, interval.contains(value));
	}
}
