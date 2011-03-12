package com.jayway.collections.immutable.sequences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jayway.collections.functions.Functions;
import com.jayway.collections.immutable.maps.Map;
import com.jayway.collections.immutable.maps.Maps;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;
import com.jayway.collections.predicates.Predicates;


public class SequenceTest {
	@Test
	public void emptySequenceIsEmpty() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithNoArgumentsCreatesAnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of();
		assertTrue("The sequence was expected to be empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithOneArgumentShouldCreateANonEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42);
		assertFalse("The sequence was expected to be non-empty",  sequence.isEmpty());
	}
	
	@Test
	public void ofWithTwoArguments() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		assertEquals("Unexpected sequense size", 2, sequence.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ofWithNullArgumentsThrowsException() throws Exception {
		Sequences.of(42, null, 41);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addWithNullArgumentsThrowsException() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		sequence.add(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addAllWithNullArgumentsThrowsException() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		sequence.addAll(45, null);
	}
	
	@Test
	public void sizeOfEmptySequenceIsZero() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertEquals("Expected size to be zero", 0, sequence.size());
	}
	
	@Test
	public void ofWithThreeArguments() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		assertEquals("Unexpected sequense size", 3, sequence.size());
	}
	
	@Test
	public void addIncreasesSizeByOne() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<Integer> newSequence = sequence.add(39);
		assertEquals("Expected size to be increased by one", 
				sequence.size() + 1, newSequence.size());
	}
	
	@Test
	public void toStringOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertEquals("Expected a valid string representation of the sequence", 
				"()", sequence.toString());
	}
	
	@Test
	public void toStringOnNonEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		assertEquals("Expected a valid string representation of the sequence", 
				"(42,41,40)", sequence.toString());
	}
	
	@Test(expected=IllegalStateException.class)
	public void callingFirstOnAnEmptySequenceThrows() throws Exception {
		Sequences.empty().first();
	}
	
	@Test(expected=IllegalStateException.class)
	public void callingRestOnAnEmptySequenceThrows() throws Exception {
		Sequences.empty().rest();
	}
	
	@Test
	public void takeFromLargeSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40, 39, 38);
		Sequence<Integer> actual = sequence.take(3);
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void takeFromSmallSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = sequence.take(5);
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void takeFromEntireSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = sequence.take(3);
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void takeFromEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> actual = sequence.take(3);
		Sequence<Integer> expected = Sequences.empty();
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void takeNegativeNumberOfElementsReturnsEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = sequence.take(-1);
		assertTrue("Expected sequence to be empty", actual.isEmpty());
	}
	
	@Test
	public void takeZeroElementsReturnsEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = sequence.take(0);
		assertTrue("Expected sequence to be empty", actual.isEmpty());
	}
	
	@Test
	public void skipNegativeNumberOfElements() throws Exception {
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = expected.skip(-1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void skipZeroElements() throws Exception {
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = expected.skip(0);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void skipEntireSequence() throws Exception {
		Sequence<Integer> expected = Sequences.of(42, 41, 40);
		Sequence<Integer> actual = expected.skip(3);
		assertTrue("Expected sequence to be empty", actual.isEmpty());
	}
	
	@Test
	public void transformSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<String> actual = sequence.transform(Functions.toStringFunction);
		Sequence<String> expected = Sequences.of("42", "41", "40");
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void transformSequenceDoesNotChangeSize() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41, 40);
		Sequence<String> actual = sequence.transform(Functions.toStringFunction);
		assertEquals("Transformed sequence should have same size", 
				sequence.size(), actual.size());
	}
	
	@Test
	public void filterSeqence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward().take(10);
		Sequence<Integer> actual = sequence.filter(Predicates.oddPredicate);
		Sequence<Integer> expected = Sequences.of(1,3,5,7,9);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void addMultipleTimes() throws Exception {
		Sequence<Integer> sequence = Sequences.of(5,6,7,8,9).add(4).addAll(0,1,2,3);
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,5,6,7,8,9);
		assertEquals("Expected sequences to be equal", expected, sequence);
	}
	
	@Test
	public void copyOfIterable() throws Exception {
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,5,6,7,8,9);
		Sequence<Integer> actual = Sequences.copyOf(expected);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void changedToSourceIterableDoesNoEffectSequence() throws Exception {
		List<Integer> elements = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,5,6,7,8,9);
		Sequence<Integer> actual = Sequences.copyOf(elements);
		elements.set(3, 13);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void changedToSourceElementsDoesNoEffectSequence() throws Exception {
		Integer[] elements = new Integer[] {0,1,2,3,4,5,6,7,8,9};
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,5,6,7,8,9);
		Sequence<Integer> actual = Sequences.of(elements);
		elements[3] = 13;
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getElementOutOfRange() throws Exception {
		Sequences.of(3,4,5,6).get(-1);
	}
	
	@Test
	public void getElementZero() throws Exception {
		int index = 0;
		Integer expected = 3;
		Integer actual = Sequences.of(3,4,5,6).get(index);
		assertEquals(String.format("Expected element at index %d", index), expected, actual);
	}
	
	@Test
	public void getElementOne() throws Exception {
		int index = 1;
		Integer expected = 4;
		Integer actual = Sequences.of(3,4,5,6).get(index);
		assertEquals(String.format("Expected element at index %d", index), expected, actual);
	}
	
	@Test
	public void getLastElement() throws Exception {
		int index = 3;
		Integer expected = 6;
		Integer actual = Sequences.of(3,4,5,6).get(index);
		assertEquals(String.format("Expected element at index %d", index), expected, actual);
	}
	
	@Test
	public void summingEmptySequenceWithReduce() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Integer expected = 0;
		Integer actual = sequence.reduce(0, Functions.plusFunction);
		assertEquals("Expected reduce on empty sequence to return start value", 
				expected , actual);
	}
	
	@Test
	public void summingSequenceWithReduce() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5,6);
		Integer expected = 21;
		Integer actual = sequence.reduce(0, Functions.plusFunction);
		assertEquals("Expected the sum of the values in the sequence.", 
				expected , actual);
	}
	
	@Test
	public void joiningStringsWithReduce() throws Exception {
		Sequence<String> sequence = Sequences.of("Hello", " ", "World", "!!!");
		String expected = "--> Hello World!!!";
		StringBuilder actual = sequence.reduce(new StringBuilder("--> "), 
				Functions.appendStringFunction);
		assertEquals("Expected joined string.", expected, actual.toString());
	}
	
	@Test
	public void reverseEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.reverse();
		assertEquals("Expected reverse sequence", expected, actual);
	}
	
	@Test
	public void reverseSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5);
		Sequence<Integer> expected = Sequences.of(5,4,3,2,1);
		Sequence<Integer> actual = sequence.reverse();
		assertEquals("Expected reverse sequence", expected, actual);
	}
	
	@Test
	public void frequenciesOnEmptySequenceReturnsAnEmptyMap() throws Exception {
		Sequence<String> sequence = Sequences.of();
		Map<String, Integer> actual = sequence.frequencies();
		Map<String, Integer> expected = Maps.empty();;
		assertEquals("Expected map to contain frequencies", expected, actual);
	}
	
	@Test
	public void frequencies() throws Exception {
		Sequence<String> sequence = Sequences.of("1", "3", "2", "3", "3", "2", "3", "1");
		Map<String, Integer> actual = sequence.frequencies();
		Map<String, Integer> expected = Maps.<String,Integer>empty()
			.put("1", 2).put("2", 2).put("3", 4);
		assertEquals("Expected map to contain frequencies", expected, actual);	
	}
}