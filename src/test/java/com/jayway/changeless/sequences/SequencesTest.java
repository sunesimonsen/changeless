package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequencesTest {

    @Test
	public void emptyReturnsAnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}

	@Test
	public void ofWithNoArgumentsCreatesAnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithOneArgumentShouldCreateANonEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42);
		assertFalse("The sequence was expected to be non-empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithTwoArgumentsShouldCreateASequenceWithTwoElements() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		assertEquals("Unexpected sequense size", 2, sequence.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ofWithNullArgumentsThrowsException() throws Exception {
		Sequences.of(42, null, 41);
	}
}