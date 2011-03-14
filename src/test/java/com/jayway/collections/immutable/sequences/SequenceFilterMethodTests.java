package com.jayway.collections.immutable.sequences;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.collections.immutable.intervals.Intervals;

public class SequenceFilterMethodTests {
	@Test
	public void filterWithInterval() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).step(3).upward().take(100);
		Sequence<Integer> actual = sequence.filter(Intervals.closed(10).closed(20));
		Sequence<Integer> expected = Sequences.of(12, 15, 18);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
}
