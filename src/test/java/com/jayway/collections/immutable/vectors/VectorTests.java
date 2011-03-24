package com.jayway.collections.immutable.vectors;

import static org.junit.Assert.*;
import org.junit.Test;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;


public class VectorTests {
	@Test
	public void emptyVectorIsEmpty() throws Exception {
		Vector<String> vector = Vectors.empty();
		assertTrue("The vector was expected to be empty",  vector.isEmpty());
	}
	
	@Test
	public void ofWithOneArguments() throws Exception {
		Vector<String> vector = Vectors.of("Foo");
		assertEquals("Unexpected vector size", 1, vector.size());
	}
	
	@Test
	public void ofWithTwoArguments() throws Exception {
		Vector<Integer> vector = Vectors.of(42, 41);
		assertEquals("Unexpected vector size", 2, vector.size());
	}
	
	@Test
	public void vectorAddTwoElementsHasAnIncreasedSize() throws Exception {
		Vector<Integer> vector = Vectors.of(42, 41).add(40).add(39);
		assertEquals("Unexpected vector size", 4, vector.size());
	}
	
	@Test
	public void vectorAddTreeElementsHasAnIncreasedSize() throws Exception {
		Vector<Integer> vector = Vectors.of(42, 41).add(40, 39, 38);
		assertEquals("Unexpected vector size", 5, vector.size());
	}
	
	@Test
	public void emptyVectorReturnsEmptySequence() throws Exception {
		Vector<Integer> vector = Vectors.empty();
		Sequence<Integer> actual = vector.sequence();
		assertTrue("Expected empty sequence",actual.isEmpty());
	}
	
	@Test
	public void vectorSequenceContainsElementsInOrder() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Sequence<Integer> expected = Sequences.of(9,8,7,6,5,0,1,2,3,4,5);
		assertEquals("Unexpected sequence", expected, vector.sequence());
	}
	
	@Test
	public void negativSkipReturnsEntireVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		assertEquals(vector, vector.skip(-1));
	}
	
	@Test
	public void skipLargerThanSizeReturnsAnEmptyVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = vector.skip(20);
		assertTrue("Expected empty vector",actual.isEmpty());;
	}
	
	@Test
	public void skipNegativeOnVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = vector.skip(0);
		Vector<Integer> expected = Vectors.of(9,8,7,6,5,0,1,2,3,4,5);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void skipZeroOnVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = vector.skip(0);
		Vector<Integer> expected = Vectors.of(9,8,7,6,5,0,1,2,3,4,5);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void skipOnVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = vector.skip(3);
		Vector<Integer> expected = Vectors.of(6,5,0,1,2,3,4,5);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void skipAndAdd() throws Exception {
		Vector<Integer> actual = Vectors.of(9,8,7,6,5).skip(3).add(0,1,2);
		Vector<Integer> expected = Vectors.of(6,5,0,1,2);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
}
