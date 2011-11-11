package com.jayway.changeless.sequences;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SequenceShuffleMethodTest {
	@Test
	public void shuffleOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.shuffle();
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void shuffleNonEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(4,5,1,8,2,3,6,7,9);
		Sequence<Integer> expected = Sequences.sort(sequence);
		Sequence<Integer> actual = sequence.shuffle();
		assertEquals("Expected sequences to be equals", expected, Sequences.sort(actual));
	}
}
