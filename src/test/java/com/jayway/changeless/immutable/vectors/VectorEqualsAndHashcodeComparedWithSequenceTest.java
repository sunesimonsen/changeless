package com.jayway.changeless.immutable.vectors;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.immutable.sequences.Sequence;
import com.jayway.changeless.immutable.sequences.Sequences;
import com.jayway.changeless.immutable.vectors.Vector;
import com.jayway.changeless.immutable.vectors.Vectors;
import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeComparedWithSequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Vectors.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Sequences.of(40,41,42);
	}
	
	@Test
	public void vectorEqualsToSequence() throws Exception {
		Vector<Integer> vector = Vectors.of(40,41,42);
		Sequence<Integer> sequence = Sequences.of(40,41,42);
		assertEquals("Expected sequence and vector to be equals", vector, sequence);
	}
	
	@Test
	public void sequenceEqualsToVector() throws Exception {
		Vector<Integer> vector = Vectors.of(40,41,42);
		Sequence<Integer> sequence = Sequences.of(40,41,42);
		assertEquals("Expected sequence and vector to be equals", sequence, vector);
	}

}
