package com.jayway.changeless.sequences;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.changeless.predicates.integers.EvenPredicate;
import com.jayway.changeless.predicates.integers.OddPredicate;



public class SequenceAllMethodTests {
	private final Sequence<Integer> oddSequence = Sequences.of(1, 3, 5, 7);
	private final Sequence<Integer> evenSequence = Sequences.of(0, 2, 4, 6);
	private final Sequence<Integer> mixedSequence = Sequences.of(1, 2, 3, 4, 5, 6, 7);
	
	@Test
	public void allReturnsTrueForEmptySequence() throws Exception {
		Sequence<Integer> empty = Sequences.<Integer>empty();
		assertTrue("empty", empty.all(new OddPredicate()));
	}
	
	@Test
	public void notAllOdd() throws Exception {
		assertFalse(mixedSequence.toString(), mixedSequence.all(new OddPredicate()));
	}
	
	@Test
	public void allOdd() throws Exception {
		assertTrue(oddSequence.toString(), oddSequence.all(new OddPredicate()));
	}
	
	@Test
	public void allEven() throws Exception {
		assertTrue(evenSequence.toString(), evenSequence.all(new EvenPredicate()));
	}
}
