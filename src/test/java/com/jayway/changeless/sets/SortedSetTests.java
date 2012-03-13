package com.jayway.changeless.sets;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.changeless.functions.Functions;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;


public class SortedSetTests {
	private Set<String> strings = SortedSets.copyOf(Sequences.from(0)
			.upward().take(10000).transform(Functions.toStringFunction)); 
	
	@Test
	public void addedIntegersCanBeFound() throws Exception {
		Set<Integer> set = SortedSets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(i);
		}

		for (int i = 0; i < numberOfElements; i++) {
			assertTrue(String.format("Expected set to contain value: %s", i),
					set.contains(i));
		}
	}

	@Test
	public void addedStringsCanBeFound() throws Exception {
		Set<String> set = SortedSets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(Integer.toString(i));
		}

		for (int i = 0; i < numberOfElements; i++) {
			assertTrue(String.format("Expected set to contain value: %s", i),
					set.contains(Integer.toString(i)));
		}
	}

	@Test
	public void emptySetIsEmpty() throws Exception {
		Set<Integer> set = SortedSets.empty();
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void nonEmptyIsNotEmpty() throws Exception {
		Set<Integer> set = SortedSets.of(1);
		assertFalse("Expected non-empty set", set.isEmpty());
	}
	
	@Test
	public void emptySetsHasSizeZero() throws Exception {
		assertEquals("Expected empty set to have a size of zero", 0, Sets
				.empty().size());
	}

	@Test
	public void sizeIsPreservedWhileAddedElementsToASet() throws Exception {
		Set<String> set = SortedSets.empty();
		int numberOfElements = 100;
		for (int i = 0; i < numberOfElements; i++) {
			Set<String> newSet = set.add(Integer.toString(i));
			assertEquals("Set size", i, set.size());
			assertEquals("Set size", i + 1, newSet.size());
			set = newSet;
		}
	}

	@Test
	public void addAnElementMultipleTimesDoesNotIncreaseSize() throws Exception {
		Set<Integer> set = SortedSets.empty();
		set = set.add(1).add(2).add(3).add(2);
		assertEquals("Size", 3, set.size());
	}

	@Test
	public void containsOnNonExistingElement() throws Exception {
		Set<Integer> set = SortedSets.empty();
		set = set.add(1).add(2).add(4);
		assertFalse("Expected to not contain 3", set.contains(3));
	}

	@Test
	public void putDuplicateElementIntoFullNote() throws Exception {
		Set<Integer> set = SortedSets.empty();
		int numberOfElements = 32;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(i);
		}
		
		set = set.add(13);
		assertEquals("Size", 32, set.size());
	}
	
	@Test
	public void removeFromFullNode() throws Exception {
		Set<Integer> set = SortedSets.copyOf(Sequences.from(0).upward().take(32));
		set = set.remove(13);
		assertEquals("Size", 31, set.size());
	}
	
	@Test
	public void remove10000Integers() throws Exception {
		Set<Integer> set = SortedSets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(i);
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			set = set.remove(i);
		}
		
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void remove10000Strings() throws Exception {
		Set<String> set = SortedSets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(Integer.toString(i));
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			set = set.remove(Integer.toString(i));
		}
		
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void removeNonExistingElementFromLeafNode() throws Exception {
		Set<Integer> set = SortedSets.empty();
		set = set.add(1);
		set = set.remove(2);
		assertEquals("Size", 1, set.size());
	}
	
	@Test
	public void removeAll() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3,4,5);
		Set<Integer> set2 = SortedSets.of(1,2,3,4,5);
		Set<Integer> actual = set1.remove(set2);
		assertTrue("Expected empty set", actual.isEmpty());
	}
	
	@Test
	public void removeNone() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3,4,5);
		Set<Integer> set2 = SortedSets.empty();
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void removeNonExisting() throws Exception {
		Set<Integer> set1 = SortedSets.empty();
		Set<Integer> set2 = SortedSets.of(1,2,3,4,5);
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void removeIntersection() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3,4,5);
		Set<Integer> set2 = SortedSets.of(2,4,5);
		Set<Integer> actual = set1.remove(set2);
		Set<Integer> expected = SortedSets.of(1,3);
		assertEquals("Remove", expected, actual);
	}
	
	@Test
	public void removeNonIntersection() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(4,5,6);
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void containsNonExistingElementFromLeafNode() throws Exception {
		Set<Integer> set = SortedSets.empty();
		set = set.add(1);
		assertFalse("Should not contain element 2", set.contains(2));
	}
	
	@Test
	public void setToSequenceOf10000Strings() throws Exception {
		Set<String> set = strings;
		for (String element : set.sequence()) {
			set = set.remove(element);
		}
		
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void setToSequenceOf10000Integers() throws Exception {
		
		Set<Integer> set = SortedSets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(i);
		}
		
		for (Integer element : set.sequence()) {
			set = set.remove(element);
		}
		
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void theSeqeunceOfAnEmptySetIsEmpty() throws Exception {
		Set<Integer> set = SortedSets.empty(); 
		Sequence<Integer> actual = set.sequence();
		Sequence<Integer> expected = Sequences.empty();
		assertEquals("Empty sequences", expected, actual);
	}
	
	@Test
	public void simpleIntersection() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(1,3,4,7);
		Set<Integer> expected = SortedSets.of(1,3);
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void fullIntersection() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(1,2,3);
		Set<Integer> expected = SortedSets.of(1,2, 3);
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionFirstSetBefore() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(4,5,6);
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionFirstSetAfter() throws Exception {
		Set<Integer> set1 = SortedSets.of(4,5,6);
		Set<Integer> set2 = SortedSets.of(1,2,3);
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionInterleavedSets() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,3,5);
		Set<Integer> set2 = SortedSets.of(2,4,6);
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void intersectionWithEmptySet() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,3,5);
		Set<Integer> set2 = SortedSets.empty();
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void emptySetIntersectionWithNonEmptySet() throws Exception {
		Set<Integer> set1 = SortedSets.empty();
		Set<Integer> set2 = SortedSets.of(1,3,5);
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void intersectionOfEmptySets() throws Exception {
		Set<Integer> set1 = SortedSets.empty();
		Set<Integer> set2 = SortedSets.empty();
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void simpleUnion() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(1,3,4,7);
		Set<Integer> expected = SortedSets.of(1,2,3,4,7);
		assertEquals("Union", expected, set1.union(set2));		
	}
	
	@Test
	public void fullUnion() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(1,2,3);
		Set<Integer> expected = SortedSets.of(1,2, 3);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOnNonIntersectingSetsFirstSetBefore() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,2,3);
		Set<Integer> set2 = SortedSets.of(4,5,6);
		Set<Integer> expected = SortedSets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOnNonIntersectingSetsFirstSetAfter() throws Exception {
		Set<Integer> set1 = SortedSets.of(4,5,6);
		Set<Integer> set2 = SortedSets.of(1,2,3);
		Set<Integer> expected = SortedSets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionInterleavedSets() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,3,5);
		Set<Integer> set2 = SortedSets.of(2,4,6);
		Set<Integer> expected = SortedSets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionWithEmptySet() throws Exception {
		Set<Integer> set1 = SortedSets.of(1,3,5);
		Set<Integer> set2 = SortedSets.empty();
		Set<Integer> expected = SortedSets.of(1,3,5);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void emptySetUnionedWithNonEmptySet() throws Exception {
		Set<Integer> set1 = SortedSets.empty();
		Set<Integer> set2 = SortedSets.of(1,3,5);
		Set<Integer> expected = SortedSets.of(1,3,5);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOfEmptySets() throws Exception {
		Set<Integer> set1 = SortedSets.empty();
		Set<Integer> set2 = SortedSets.empty();
		Set<Integer> expected = SortedSets.empty();
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void toSeqenceAndBack() throws Exception {
		Set<Integer> set = SortedSets.of(1,2,3,4,5,6);
		Set<Integer> actual = SortedSets.copyOf(set.sequence());
		assertEquals("Expected sets to be equals", set, actual);
	}
	
	@Test
	public void symmetricDifferentWithNoElements() throws Exception {
		Set<Integer> a = SortedSets.of(1,2,3,4);
		Set<Integer> b = SortedSets.empty();
		Set<Integer> actual = a.symmetricDifference(b);
		Set<Integer> expected = a;
		assertEquals("Expected sets to be equals", expected, actual);
	}
	
	@Test
	public void symmetricDifferentOnEmptySet() throws Exception {
		Set<Integer> a = SortedSets.empty();
		Set<Integer> b = SortedSets.of(1,2,3,4);
		Set<Integer> actual = a.symmetricDifference(b);
		Set<Integer> expected = b;
		assertEquals("Expected sets to be equals", expected, actual);
	}
	
	@Test
	public void symmetricDifferentWithNoSharedElements() throws Exception {
		Set<Integer> a = SortedSets.of(0,5);
		Set<Integer> b = SortedSets.of(1,2,3,4);
		Set<Integer> actual = a.symmetricDifference(b);
		Set<Integer> expected = SortedSets.of(0,1,2,3,4,5);
		assertEquals("Expected sets to be equals", expected, actual);
	}
	
	@Test
	public void symmetricDifferentWithSharedElements() throws Exception {
		Set<Integer> a = SortedSets.of(0,2,3,5);
		Set<Integer> b = SortedSets.of(1,2,3,4);
		Set<Integer> actual = a.symmetricDifference(b);
		Set<Integer> expected = SortedSets.of(0,1,4,5);
		assertEquals("Expected sets to be equals", expected, actual);
	}
	
	@Test
	public void theSequenceOfASortedSetShouldBeSorted() throws Exception {
		Set<Integer> set = SortedSets.of(4,9,8,1,6,0,3,5,2,7);
		Sequence<Integer> expected = Sequences.of(0,1,2,3,4,5,6,7,8,9);
		Sequence<Integer> actual = set.sequence();
		assertEquals("Expected sequence to sorted", expected, actual);
	}
	
	@Test
	public void theToStringOfASortedSetShouldBeSorted() throws Exception {
		Set<Integer> set = SortedSets.of(4,9,8,1,6,0,3,5,2,7);
		String expected = "(0,1,2,3,4,5,6,7,8,9)";
		String actual = set.toString();
		assertEquals("Expected string to sorted", expected, actual);
	}
}

