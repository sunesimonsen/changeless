package com.jayway.collections.immutable.sets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.collections.functions.Functions;
import com.jayway.collections.immutable.sequences.Sequences;


public class SetTests {
	private Set<String> strings = Sets.of(Sequences.from(0)
			.upward().take(10000).transform(Functions.toStringFunction)); 
	
	@Test
	public void addedIntegersCanBeFound() throws Exception {
		Set<Integer> set = Sets.empty();
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
		Set<String> set = Sets.empty();
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
		assertTrue("Expected empty set", Sets.empty().isEmpty());
	}
	
	@Test
	public void nonEmptyIsNotEmpty() throws Exception {
		Set<Integer> set = Sets.of(1);
		assertFalse("Expected non-empty set", set.isEmpty());
	}
	
	@Test
	public void emptySetsHasSizeZero() throws Exception {
		assertEquals("Expected empty set to have a size of zero", 0, Sets
				.empty().size());
	}

	@Test
	public void sizeIsPreservedWhileAddedElementsToASet() throws Exception {
		Set<String> set = Sets.empty();
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
		Set<Integer> set = Sets.empty();
		set = set.add(1).add(2).add(3).add(2);
		assertEquals("Size", 3, set.size());
	}

	@Test
	public void containsOnNonExistingElement() throws Exception {
		Set<Integer> set = Sets.empty();
		set = set.add(1).add(2).add(4);
		assertFalse("Expected to not contain 3", set.contains(3));
	}

	@Test
	public void addingTwoElementsUnequalButWithSameHash() throws Exception {
		Set<TestElement> set = Sets.empty();
		set = set.add(new TestElement(0)).add(new TestElement(1)).add(
				new TestElement(1)).add(new TestElement(100)).add(
				new TestElement(101));

		assertEquals("Size", 4, set.size());
		assertTrue("Expected set to contain test element 0", set
				.contains(new TestElement(0)));
		assertTrue("Expected set to contain test element 1", set
				.contains(new TestElement(1)));
		assertTrue("Expected set to contain test element 100", set
				.contains(new TestElement(100)));
		assertTrue("Expected set to contain test element 101", set
				.contains(new TestElement(101)));
	}

	@Test
	public void putDuplicateElementIntoFullNote() throws Exception {
		Set<Integer> set = Sets.empty();
		int numberOfElements = 32;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(i);
		}
		
		set = set.add(13);
		assertEquals("Size", 32, set.size());
	}
	
	@Test
	public void removeFromFullNode() throws Exception {
		Set<Integer> set = Sets.of(Sequences.from(0).upward().take(32));
		set = set.remove(13);
		assertEquals("Size", 31, set.size());
	}
	
	@Test
	public void removeNonExistingElementFromFullNode() throws Exception {
		Set<TestElement> set = Sets.empty();
		int numberOfElements = 32;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(new TestElement(i));
		}
		
		set = set.remove(new TestElement(100));
		set = set.remove(new TestElement(100));
		assertEquals("Size", 32, set.size());
	}
	
	@Test
	public void remove10000Integers() throws Exception {
		Set<Integer> set = Sets.empty();
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
		Set<String> set = Sets.empty();
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
	public void remove10000TestElements() throws Exception {
		Set<TestElement> set = Sets.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			set = set.add(new TestElement(i));
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			set = set.remove(new TestElement(i));
		}
		
		assertTrue("Expected empty set", set.isEmpty());
	}
	
	@Test
	public void removeNonExistingElementFromLeafNode() throws Exception {
		Set<Integer> set = Sets.empty();
		set = set.add(1);
		set = set.remove(2);
		assertEquals("Size", 1, set.size());
	}
	
	@Test
	public void removeAll() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3,4,5);
		Set<Integer> set2 = Sets.of(1,2,3,4,5);
		Set<Integer> actual = set1.remove(set2);
		assertTrue("Expected empty set", actual.isEmpty());
	}
	
	@Test
	public void removeNone() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3,4,5);
		Set<Integer> set2 = Sets.empty();
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void removeNonExisting() throws Exception {
		Set<Integer> set1 = Sets.empty();
		Set<Integer> set2 = Sets.of(1,2,3,4,5);
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void removeIntersection() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3,4,5);
		Set<Integer> set2 = Sets.of(2,4,5);
		Set<Integer> actual = set1.remove(set2);
		Set<Integer> expected = Sets.of(1,3);
		assertEquals("Remove", expected, actual);
	}
	
	@Test
	public void removeNonIntersection() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(4,5,6);
		Set<Integer> actual = set1.remove(set2);
		assertEquals("Remove", set1, actual);
	}
	
	@Test
	public void containsNonExistingElementFromLeafNode() throws Exception {
		Set<Integer> set = Sets.empty();
		set = set.add(1);
		assertFalse("Should not contain element 2", set.contains(2));
	}
	
	@Test
	public void containsNonExistingElementFromCollisionNode() throws Exception {
		Set<TestElement> set = Sets.empty();
		set = set.add(new TestElement(1));
		set = set.add(new TestElement(2));
		assertFalse("Should not contain element 2", set.contains(new TestElement(3)));
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
		
		Set<Integer> set = Sets.empty();
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
		assertEquals("Empty sequences", Sequences.empty(), Sets.empty().sequence());
	}
	
	@Test
	public void simpleIntersection() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(1,3,4,7);
		Set<Integer> expected = Sets.of(1,3);
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void fullIntersection() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(1,2,3);
		Set<Integer> expected = Sets.of(1,2, 3);
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionFirstSetBefore() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(4,5,6);
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionFirstSetAfter() throws Exception {
		Set<Integer> set1 = Sets.of(4,5,6);
		Set<Integer> set2 = Sets.of(1,2,3);
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void noIntersectionInterleavedSets() throws Exception {
		Set<Integer> set1 = Sets.of(1,3,5);
		Set<Integer> set2 = Sets.of(2,4,6);
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void intersectionWithEmptySet() throws Exception {
		Set<Integer> set1 = Sets.of(1,3,5);
		Set<Integer> set2 = Sets.empty();
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void emptySetIntersectionWithNonEmptySet() throws Exception {
		Set<Integer> set1 = Sets.empty();
		Set<Integer> set2 = Sets.of(1,3,5);
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void intersectionOfEmptySets() throws Exception {
		Set<Integer> set1 = Sets.empty();
		Set<Integer> set2 = Sets.empty();
		Set<Integer> expected = Sets.empty();
		assertEquals("Intersection", expected, set1.intersection(set2));
	}
	
	@Test
	public void simpleUnion() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(1,3,4,7);
		Set<Integer> expected = Sets.of(1,2,3,4,7);
		assertEquals("Union", expected, set1.union(set2));		
	}
	
	@Test
	public void fullUnion() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(1,2,3);
		Set<Integer> expected = Sets.of(1,2, 3);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOnNonIntersectingSetsFirstSetBefore() throws Exception {
		Set<Integer> set1 = Sets.of(1,2,3);
		Set<Integer> set2 = Sets.of(4,5,6);
		Set<Integer> expected = Sets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOnNonIntersectingSetsFirstSetAfter() throws Exception {
		Set<Integer> set1 = Sets.of(4,5,6);
		Set<Integer> set2 = Sets.of(1,2,3);
		Set<Integer> expected = Sets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionInterleavedSets() throws Exception {
		Set<Integer> set1 = Sets.of(1,3,5);
		Set<Integer> set2 = Sets.of(2,4,6);
		Set<Integer> expected = Sets.of(1,2,3,4,5,6);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionWithEmptySet() throws Exception {
		Set<Integer> set1 = Sets.of(1,3,5);
		Set<Integer> set2 = Sets.empty();
		Set<Integer> expected = Sets.of(1,3,5);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void emptySetUnionedWithNonEmptySet() throws Exception {
		Set<Integer> set1 = Sets.empty();
		Set<Integer> set2 = Sets.of(1,3,5);
		Set<Integer> expected = Sets.of(1,3,5);
		assertEquals("Union", expected, set1.union(set2));
	}
	
	@Test
	public void unionOfEmptySets() throws Exception {
		Set<Integer> set1 = Sets.empty();
		Set<Integer> set2 = Sets.empty();
		Set<Integer> expected = Sets.empty();
		assertEquals("Union", expected, set1.union(set2));
	}

	private static class TestElement {
		private final int value;

		public TestElement(int value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			return value < 100 ? 0 : value;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestElement other = (TestElement) obj;
			if (value != other.value)
				return false;
			return true;
		}
	}
}

