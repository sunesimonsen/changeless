package com.jayway.changeless.sequences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.predicates.Predicates;

public class SequenceFindMethodTest {
	@Test
	public void findWithNoMatch() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,4,5);
		Optional<Integer> result = sequence.find(Predicates.equalsPredicate(3));
		assertTrue("Did no expected any result", result.hasNoValue());
	}
	
	@Test
	public void findWithMatch() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5);
		Optional<Integer> expected = Optional.valueOf(3);
		Optional<Integer> actual = sequence.find(Predicates.equalsPredicate(3));
		assertEquals("Expected result", expected, actual);
	}
	
	@Test
	public void findOnInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(1).upward();
		Optional<Integer> expected = Optional.valueOf(42);
		Optional<Integer> actual = sequence.find(Predicates.equalsPredicate(42));
		assertEquals("Expected result", expected, actual);
	}
}
