package com.jayway.changeless.sequences;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.changeless.predicates.integers.EvenPredicate;

public class SequenceSkipWhileMethodTest {
	@Test
	public void skipWhileOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.skipWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void skipWhileOnSequenceWithNoMatch() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5,6,7,8,9);
		Sequence<Integer> expected = Sequences.of(1,2,3,4,5,6,7,8,9);
		Sequence<Integer> actual = sequence.skipWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void skipWhileOnSequenceWithMatches() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2,4,6,7,8,9);
		Sequence<Integer> expected = Sequences.of(7,8,9);
		Sequence<Integer> actual = sequence.skipWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
}
