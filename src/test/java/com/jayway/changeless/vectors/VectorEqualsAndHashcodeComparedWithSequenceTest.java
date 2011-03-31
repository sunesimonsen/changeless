package com.jayway.changeless.vectors;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.vectors.Vector;
import com.jayway.changeless.vectors.Vectors;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
