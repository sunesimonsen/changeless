package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequencesTest {

    @Test
	public void empty_should_return_an_empty_sequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}

	@Test
	public void of_with_no_arguments_creates_an_empty_sequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}
	
	@Test
	public void of_with_one_argument_should_create_a_non_empty_sequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42);
		assertFalse("The sequence was expected to be non-empty",  sequence.isEmpty());
	}
	
	@Test
	public void of_with_two_arguments_should_create_a_sequence_of_size_2() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		assertEquals("Unexpected sequense size", 2, sequence.size());
	}

	@Test
	public void of_with_three_arguments_should_create_a_sequence_of_size_3() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		assertEquals("Unexpected sequense size", 3, sequence.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void of_with_null_arguments_throws_exception() throws Exception {
		Sequences.of(42, null, 41);
	}
}