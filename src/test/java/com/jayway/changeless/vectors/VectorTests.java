package com.jayway.changeless.vectors;

import static org.junit.Assert.*;
import org.junit.Test;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.vectors.Vector;
import com.jayway.changeless.vectors.Vectors;


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
	public void addNothingToEmpty() throws Exception {
		Vector<Integer> vector = Vectors.empty();
		Vector<Integer> actual = vector.add();
		assertTrue("Expected empty vector",actual.isEmpty());
	}
	
	@Test
	public void addNothingToVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = vector.add();
		assertEquals("Expected vectors to be equals",vector, actual);
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
		assertTrue("Expected empty vector",actual.isEmpty());
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
	
	@Test
	public void skipOnVectorSequence() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Sequence<Integer> actual = vector.sequence().skip(3);
		Sequence<Integer> expected = Sequences.of(6,5,0,1,2,3,4,5);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void toSequenceAndBack() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		Vector<Integer> actual = Vectors.copyOf(vector.sequence());
		assertEquals("Expected sequences to be equals",vector, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getWithNegativeIndexThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		vector.get(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getWithIndexLargerThanSizeThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		vector.get(12);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithNegativeIndexThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		vector.set(-1, 13);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithIndexLargerThanSizeThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).add(0,1,2,3,4,5);
		vector.set(12, 13);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithNegativeIndexOnPrefixVectorThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).take(3);
		vector.set(-1, 13);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithIndexLargerThanSizeOnPrefixVectorThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).take(3);
		vector.set(3, 13);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithNegativeIndexOnSuffixVectorThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).skip(3);
		vector.set(-1, 13);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWithIndexLargerThanSizeOnSuffixVectorThrows() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).skip(3);
		vector.set(2, 13);
	}
	
	@Test
	public void setOnVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.set(3, 13);
		Vector<Integer> expected = Vectors.of(9,8,7,13,5);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void setOnPrefixVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).take(3);
		Vector<Integer> actual = vector.set(1, 13);
		Vector<Integer> expected = Vectors.of(9,13,7);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void setOnSuffixVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5).skip(2);
		Vector<Integer> actual = vector.set(1, 13);
		Vector<Integer> expected = Vectors.of(7,13,5);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void takeNegativeReturnEmptyVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.take(-1);
		assertEquals("Expected vectors to be equals",Vectors.empty(), actual);
	}
	
	@Test
	public void takeZeroReturnEmptyVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.take(0);
		assertEquals("Expected vectors to be equals",Vectors.empty(), actual);
	}
	
	@Test
	public void takeLargerThanSize() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.take(6);
		assertEquals("Expected vectors to be equals",vector, actual);
	}
	
	@Test
	public void takeAndAdd() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.take(3).add(4,3);
		Vector<Integer> expected = Vectors.of(9,8,7,4,3);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
	
	@Test
	public void takeAndAddMoreThanOriginalVector() throws Exception {
		Vector<Integer> vector = Vectors.of(9,8,7,6,5);
		Vector<Integer> actual = vector.take(3).add(4,3,2,1);
		Vector<Integer> expected = Vectors.of(9,8,7,4,3,2,1);
		assertEquals("Expected vectors to be equals",expected, actual);
	}
}
