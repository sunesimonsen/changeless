package com.jayway.changeless.sequences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.Functions;
import com.jayway.changeless.functions.integers.PlusFunction;
import com.jayway.changeless.functions.strings.AppendStringFunction;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.predicates.integers.EvenPredicate;
import com.jayway.changeless.predicates.integers.OddPredicate;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.tuples.Tuples;


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
		sequence.add((Integer)null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addAllWithNullArgumentsThrowsException() throws Exception {
		Sequence<Integer> sequence = Sequences.of(42, 41);
		sequence.add(45, null);
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
		Sequence<Integer> actual = sequence.filter(new OddPredicate());
		Sequence<Integer> expected = Sequences.of(1,3,5,7,9);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void integerSeqenceWithNegativeStepThrows() throws Exception {
		Sequences.from(0).step(-2).downward();
	}
	
	@Test
	public void integerSeqenceWithSteps() throws Exception {
		Sequence<Integer> actual = Sequences.from(0).step(2).upward().take(5);
		Sequence<Integer> expected = Sequences.of(0,2,4,6,8);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void integerSeqenceDownwardWithSteps() throws Exception {
		Sequence<Integer> actual = Sequences.from(0).step(2).downward().take(5);
		Sequence<Integer> expected = Sequences.of(0,-2,-4,-6,-8);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void addMultipleTimes() throws Exception {
		Sequence<Integer> sequence = Sequences.of(5,6,7,8,9).add(4).add(0,1,2,3);
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
		Integer actual = sequence.reduce(0, new PlusFunction());
		assertEquals("Expected reduce on empty sequence to return start value", 
				expected , actual);
	}
	
	@Test
	public void summingSequenceWithReduce() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5,6);
		Integer expected = 21;
		Integer actual = sequence.reduce(0, new PlusFunction());
		assertEquals("Expected the sum of the values in the sequence.", 
				expected , actual);
	}
	
	@Test
	public void joiningStringsWithReduce() throws Exception {
		Sequence<String> sequence = Sequences.of("Hello", " ", "World", "!!!");
		String expected = "--> Hello World!!!";
		StringBuilder actual = sequence.reduce(new StringBuilder("--> "), 
				new AppendStringFunction());
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
	public void evenSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(-10).upward().take(20);
		Sequence<Integer> actual = sequence.filter(new EvenPredicate());
		Sequence<Integer> expected = Sequences.from(-10).step(2).upward().take(10);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void frequenciesOnEmptySequenceReturnsAnEmptyMap() throws Exception {
		Sequence<String> sequence = Sequences.of();
		Map<String, Integer> actual = sequence.frequencies();
		Map<String, Integer> expected = Maps.empty();
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
	
	@Test
	public void toSequenceAndBack() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4,5);
		Sequence<Integer> actual = sequence.sequence();
		assertEquals("Expected sequences to be equals",sequence, actual);
	}
	
	@Test
	public void filterOnInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(-10).upward();
		Sequence<Integer> actual = sequence.filter(new EvenPredicate()).take(10);
		Sequence<Integer> expected = Sequences.from(-10).step(2).upward().take(10);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void transformOnInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(-10).upward();
		Sequence<String> actual = sequence.transform(Functions.toStringFunction).take(3);
		Sequence<String> expected = Sequences.of("-10", "-9", "-8");
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void addIterableToSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(-10).upward().take(3);
		Sequence<Integer> actual = sequence.add(sequence.reverse());
		Sequence<Integer> expected = Sequences.of(-8,-9,-10,-10,-9,-8);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void getElementOnInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(-10).upward();
		assertEquals("Expected sequences to be equals", -7, (int)sequence.get(3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getNegativeThrows() throws Exception {
		Sequences.from(-10).upward().get(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getLargerThanSequenceThrows() throws Exception {
		Sequences.from(-10).upward().take(10).get(11);
	}
	
	@Test
	public void lazyCopyOfSequence() throws Exception {
		Sequence<Integer> actual = Sequences.lazyCopyOf(Arrays.asList(1, 2, 3, 4, 5));
		Sequence<Integer> expected = Sequences.of(1, 2, 3, 4, 5);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void sequenceProducer() throws Exception {
		Sequence<String> sequence = Sequences.from("a").produce(new Fn<String, Optional<String>>() {
			@Override
			public Optional<String> apply(String input) {
				return Optional.valueOf(input + input);
			}
		});
		Sequence<String> actual = sequence.take(4);
		Sequence<String> expected = Sequences.of("a", "aa", "aaaa", "aaaaaaaa");
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void minOnEmptySequenceThrows() throws Exception {
		Sequences.min(Sequences.<Integer>empty());
	}
	
	@Test
	public void minOnSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(5,6,2,8,1,2);
		Integer actual = Sequences.min(sequence);
		Integer expected = 1;
		assertEquals("min", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void maxOnEmptySequenceThrows() throws Exception {
		Sequences.max(Sequences.<Integer>empty());
	}
	
	@Test
	public void maxOnSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(5,6,2,8,1,2);
		Integer actual = Sequences.max(sequence);
		Integer expected = 8;
		assertEquals("min", expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void partitionWithNegativeArgumentThrows() throws Exception {
		Sequences.of(1,2,3).partition(-1);
	}
	
	@Test
	public void partitionOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Sequence<Integer>> actual = sequence.partition(2);
		assertTrue("Expected sequence to be empty", actual.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void partitionSequenceDivisibleOfN() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward().take(12);
		Sequence<Sequence<Integer>> actual = sequence.partition(3);
		Sequence<Sequence<Integer>> expected = 
			Sequences.of(
				Sequences.of(0,1,2),
				Sequences.of(3,4,5),
				Sequences.of(6,7,8),
				Sequences.of(9,10,11));
		
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void partitionSequenceNotDivisibleOfN() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward().take(10);
		Sequence<Sequence<Integer>> actual = sequence.partition(3);
		Sequence<Sequence<Integer>> expected = 
			Sequences.of(
				Sequences.of(0,1,2),
				Sequences.of(3,4,5),
				Sequences.of(6,7,8),
				Sequences.of(9));
		
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void partitionInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward();
		Sequence<Sequence<Integer>> actual = sequence.partition(3).take(4);
		Sequence<Sequence<Integer>> expected = 
			Sequences.of(
				Sequences.of(0,1,2),
				Sequences.of(3,4,5),
				Sequences.of(6,7,8),
				Sequences.of(9,10,11));
		
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void partitionLargerThanSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward().take(10);
		Sequence<Sequence<Integer>> actual = sequence.partition(20);
		Sequence<Sequence<Integer>> expected = Sequences.of(sequence);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void cycleEmptySequence() throws Exception {
		Sequence<Object> sequence = Sequences.empty().cycle();
		assertTrue("Expected an empty sequence", sequence.isEmpty());
	}
	
	@Test
	public void cycleSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4);
		Sequence<Integer> actual = sequence.cycle().take(10); 
		Sequence<Integer> expected = Sequences.of(1,2,3,4,1,2,3,4,1,2);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void zipSequence() throws Exception {
		Sequence<Integer> sequence1 = Sequences.of(1,2,3,4);
		Sequence<String> sequence2 = Sequences.of("5","6","7","8","9","10");
		
		Sequence<Tuple<Integer,String>> actual = sequence1.zip(sequence2); 
		Sequence<Tuple<Integer,String>> expected = 
			Sequences.of(Tuples.of(1,"5"), Tuples.of(2,"6"), Tuples.of(3,"7"), Tuples.of(4,"8"));
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void upzipSequence() throws Exception {
		Sequence<Integer> sequence1 = Sequences.of(1,2,3,4);
		Sequence<String> sequence2 = Sequences.of("5","6","7","8","9","10");
		Sequence<Tuple<Integer, String>> zipped = sequence1.zip(sequence2);
		
		Tuple<Sequence<Integer>,Sequence<String>> actual = Sequences.unzip(zipped); 
		Tuple<Sequence<Integer>,Sequence<String>> expected = Tuples.of(sequence1, sequence2.take(4));
 		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void insertNull() throws Exception {
		Sequences.of(1,2,3).insertAt(0, (Integer)null);
	}
	
	@Test
	public void insertAtNegativeIndex() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1, 2, 3);
		Sequence<Integer> actual = sequence.insertAt(-1, 0);
		Sequence<Integer> expected = Sequences.of(0, 1, 2, 3);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAtFront() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2, 3, 4);
		Sequence<Integer> actual = sequence.insertAt(0, 0, 1);
		Sequence<Integer> expected = Sequences.of(0, 1, 2, 3, 4);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAtEnd() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2, 3, 4);
		Sequence<Integer> actual = sequence.insertAt(3, 0, 1);
		Sequence<Integer> expected = Sequences.of(2, 3, 4, 0, 1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAtAfterEnd() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2, 3, 4);
		Sequence<Integer> actual = sequence.insertAt(100, 0, 1);
		Sequence<Integer> expected = Sequences.of(2, 3, 4, 0, 1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAtIntoEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> actual = sequence.insertAt(0, 0, 1);
		Sequence<Integer> expected = Sequences.of(0, 1);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertNothing() throws Exception {
		Sequence<Integer> sequence = Sequences.of(2, 3, 4);
		Sequence<Integer> actual = sequence.insertAt(0);
		Sequence<Integer> expected = sequence;
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAtIntoInfiniteSequence() throws Exception {
		Sequence<Integer> sequence = Sequences.from(0).upward();
		Sequence<Integer> actual = sequence.insertAt(5, 0, 0).take(10);
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,0,0,5,6,7);
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void isSizeOnEmpty() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertTrue("Expected size 0", sequence.isSize(0));
	}
	
	@Test
	public void isNotSizeOnEmpty() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		assertFalse("Expected size 0", sequence.isSize(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void isNegativeSize() throws Exception {
		Sequences.empty().isSize(-10);
	}
	
	@Test
	public void isSizeLessThanSequenceSize() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4);
		assertFalse("Expected size 4", sequence.isSize(2));
	}
	
	@Test
	public void isSizeGreaterThanSequenceSize() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4);
		assertFalse("Expected size 4", sequence.isSize(6));
	}
	
	@Test
	public void isSizeEqualsToSequenceSize() throws Exception {
		Sequence<Integer> sequence = Sequences.of(1,2,3,4);
		assertTrue("Expected size 4", sequence.isSize(4));
	}
}