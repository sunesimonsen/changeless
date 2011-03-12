package com.jayway.collections.immutable.vectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.collections.immutable.vectors.Vector;
import com.jayway.collections.immutable.vectors.Vectors;


public class VectorTests {
	@Test
	public void emptyVectorIsEmpty() throws Exception {
		Vector<String> sequence = Vectors.empty();
		assertTrue("The vector was expected to be empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithOneArguments() throws Exception {
		Vector<String> sequence = Vectors.of("Foo");
		assertEquals("Unexpected vector size", 1, sequence.size());
	}
	
	@Test
	public void ofWithTwoArguments() throws Exception {
		Vector<Integer> sequence = Vectors.of(42, 41);
		assertEquals("Unexpected vector size", 2, sequence.size());
	}
}
