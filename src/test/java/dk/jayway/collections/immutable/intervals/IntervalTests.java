package dk.jayway.collections.immutable.intervals;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTests {
	@Test
	public void containInClosedInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).close(4);
		assertContains(interval, 2);
	}
	
	@Test
	public void beforeClosedInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).close(4);
		assertOutsideInterval(interval, 0);
	}
	
	@Test
	public void afterClosedInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).close(4);
		assertOutsideInterval(interval, 5);
	}

	@Test
	public void containInClosedIntervalStart() throws Exception {
		Interval<Integer> interval = Intervals.close(1).close(4);
		assertContains(interval, 1);
	}
	
	@Test
	public void containInClosedIntervalEnd() throws Exception {
		Interval<Integer> interval = Intervals.close(1).close(4);
		assertContains(interval, 4);
	}
	
	@Test
	public void containInRightOpenInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).open(4);
		assertContains(interval, 2);
	}
	
	@Test
	public void beforeRightOpenInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).open(4);
		assertOutsideInterval(interval, 0);
	}
	
	@Test
	public void afterRightOpenInterval() throws Exception {
		Interval<Integer> interval = Intervals.close(1).open(4);
		assertOutsideInterval(interval, 4);
	}
	
	@Test
	public void containInRightOpenIntervalStart() throws Exception {
		Interval<Integer> interval = Intervals.close(1).open(4);
		assertContains(interval, 1);
	}
	
	@Test
	public void containInRightOpenIntervalEnd() throws Exception {
		Interval<Integer> interval = Intervals.close(1).open(4);
		assertContains(interval, 3);
	}
	
	private void assertOutsideInterval(Interval<Integer> interval, int value) {
		String message = String.format("Expect value '%s' to be outside interval '%s'", value, interval);
		assertFalse(message, interval.Contains(value));
	}

	private <T extends Comparable<T>> void assertContains(Interval<T> interval, T value) {
		String message = String.format("Expect value '%s' to be contained in interval '%s'", value, interval);
		assertTrue(message, interval.Contains(value));
	}
}
