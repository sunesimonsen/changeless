package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceDistinctMethodTest {
	@Test
	public void distinctOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.distinct();
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void distinctOnSequenceDuplicates() throws Exception {
		Sequence<Integer> sequence = Sequences.of(5,3,4,2,4,2,3,4,6,5,7,1,3,2,4);
		Sequence<Integer> expected = Sequences.of(5,3,4,2,6,7,1);
		Sequence<Integer> actual = sequence.distinct();
		assertEquals("Expected sequences to be equals", expected, actual);
	}
}
