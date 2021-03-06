package com.jayway.changeless.vectors;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.vectors.Vector;
import com.jayway.changeless.vectors.Vectors;


public class VectorEqualsAndHashcodeComparedWithSequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Vectors.of(42,41,40);
	}

	@Override
	protected Object notX() {
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
