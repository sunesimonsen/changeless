package com.jayway.collections.immutable.sequences;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;
import com.jayway.collections.predicates.Predicates;



public class SequenceAllMethodTests {
	private final Sequence<Integer> oddSequence = Sequences.of(1, 3, 5, 7);
	private final Sequence<Integer> evenSequence = Sequences.of(0, 2, 4, 6);
	private final Sequence<Integer> mixedSequence = Sequences.of(1, 2, 3, 4, 5, 6, 7);
	
	@Test
	public void allReturnsTrueForEmptySequence() throws Exception {
		Sequence<Integer> empty = Sequences.<Integer>empty();
		assertTrue("empty", empty.all(Predicates.oddPredicate));
	}
	
	@Test
	public void notAllOdd() throws Exception {
		assertFalse(mixedSequence.toString(), mixedSequence.all(Predicates.oddPredicate));
	}
	
	@Test
	public void allOdd() throws Exception {
		assertTrue(oddSequence.toString(), oddSequence.all(Predicates.oddPredicate));
	}
	
	@Test
	public void allEven() throws Exception {
		assertTrue(evenSequence.toString(), evenSequence.all(Predicates.evenPredicate));
	}
}
