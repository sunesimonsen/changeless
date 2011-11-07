package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.predicates.integers.EvenPredicate;

public class SequenceTakeWhileMethodTest {
	@Test
	public void takeWhileOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.takeWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void takeWhileOnSequenceWithNoMatch() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5,6,7,8,9);
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.takeWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void takeWhileOnSequenceWithMatches() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2,4,6,7,8,9);
		Sequence<Integer> expected = Sequences.of(2,4,6);
		Sequence<Integer> actual = sequence.takeWhile(new EvenPredicate());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
}
