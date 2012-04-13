package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.functions.integers.PlusFunction;
import com.jayway.changeless.test.IndexIntoString;

public class EmptySequenceTest {
    private Sequence<Integer> subject = Sequences.empty();

	@Test
	public void to_string_returns_string_representation_of_sequence() throws Exception {
		assertEquals("Expected a valid string representation of the sequence", 
				"()", subject.toString());
	}

	@Test
	public void size_of_empty_sequence_should_be_zero() throws Exception {
		assertEquals("Expected size to be zero", 0, subject.size());
	}

	@Test
	public void take_should_return_an_empty_sequence() throws Exception {
		Sequence<Integer> actual = subject.take(3);
		Sequence<Integer> expected = Sequences.empty();
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void transformIndexed_should_return_an_empty_sequence() throws Exception {
		Sequence<String> actual = subject.transformIndexed(new IndexIntoString());
		assertTrue("Expected sequence to empty", actual.isEmpty());
	}

	@Test
	public void reduce_should_return_an_empty_sequence() throws Exception {
		Integer expected = 0;
		Integer actual = subject.reduce(0, new PlusFunction());
		assertEquals("Expected reduce on empty sequence to return start value", 
				expected , actual);
	}
	
	@Test
	public void reverse_should_return_an_empty_sequence() throws Exception {
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = subject.reverse();
		assertEquals("Expected reverse sequence", expected, actual);
	}

	@Test
    public void frequencies_should_return_an_empty_map() throws Exception {
        Sequence<String> sequence = Sequences.empty();
		Map<String, Integer> actual = sequence.frequencies();
		Map<String, Integer> expected = Maps.empty();
		assertEquals("Expected map to contain frequencies", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void max_should_throw() throws Exception {
		Sequences.max(subject);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void min_should_throw() throws Exception {
		Sequences.min(subject);
	}
	
	@Test
	public void partition_should_return_an_empty_sequence() throws Exception {
		Sequence<Sequence<Integer>> actual = subject.partition(2);
		assertTrue("Expected sequence to be empty", actual.isEmpty());
	}
	
	@Test
	public void cycle_should_return_an_empty_sequence() throws Exception {
		Sequence<Integer> actual = subject.cycle();
		assertTrue("Expected an empty sequence", actual.isEmpty());
	}
	
	@Test
    public void insertAt_should_return_a_sequence_containing_inserted_element() throws Exception {
		Sequence<Integer> actual = subject.insertAt(0, 0, 1);
		Sequence<Integer> expected = Sequences.of(0, 1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void should_have_size_zero() throws Exception {
		assertTrue("Expected size 0", subject.isSize(0));
	}
	
	@Test
	public void should_not_have_size_ten() throws Exception {
		assertFalse("Expected size 0", subject.isSize(10));
	}
	
	@Test(expected=IllegalStateException.class)
	public void first_should_throw() throws Exception {
		subject.first();
	}
	
	@Test(expected=IllegalStateException.class)
	public void rest_should_throw() throws Exception {
		subject.rest();
	}
	
}