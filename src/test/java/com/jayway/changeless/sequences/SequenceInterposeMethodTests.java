package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceInterposeMethodTests {
	@Test
	public void interposeOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.interpose(1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void interposeWithSameType() throws Exception {
		Sequence<String> sequence = Sequences.of("one", "two", "three");
		Sequence<String> expected = Sequences.of("one", ",", "two", ",", "three");
		Sequence<String> actual = sequence.interpose(",");
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void interposeSubclass() throws Exception {
		Sequence<Object> sequence = Sequences.<Object>of("one", "two", "three");
		Sequence<Object> expected = Sequences.<Object>of("one", ',', "two", ',', "three");
		Sequence<Object> actual = sequence.interpose(',');
		assertEquals("Expected sequences to be equal", expected, actual);
	}
}
