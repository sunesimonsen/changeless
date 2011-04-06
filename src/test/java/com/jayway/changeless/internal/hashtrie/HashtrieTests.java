package com.jayway.changeless.internal.hashtrie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class HashtrieTests {
	@Test
	public void addedIntegersCanBeFound() throws Exception {
		Node<Integer> trie = HashTries.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			trie = trie.add(0,i,i);
		}

		for (int i = 0; i < numberOfElements; i++) {
			assertTrue(String.format("Expected hash trie to contain value: %s", i),
					trie.get(i,i).hasValue());
		}
	}

	@Test
	public void emptyHashTrieIsEmpty() throws Exception {
		assertTrue("Expected empty set", HashTries.empty().isEmpty());
	}
	
	@Test
	public void nonEmptyIsNotEmpty() throws Exception {
		Node<Object> trie = HashTries.empty().add(0, 1, 1);
		assertFalse("Expected non-empty set", trie.isEmpty());
	}
	
	@Test
	public void emptyHashTrieHasSizeZero() throws Exception {
		Node<Object> trie = HashTries.empty();
		assertEquals("Expected empty hash trie to have a size of zero", 0, trie.size());
	}

	@Test
	public void sizeIsPreservedWhileAddedElementsToAHashTrie() throws Exception {
		Node<Object> trie = HashTries.empty();
		int numberOfElements = 100;
		for (int i = 0; i < numberOfElements; i++) {
			Node<Object> newTrie = trie.add(0,i,i);
			assertEquals("Trie size", i, trie.size());
			assertEquals("Trie size", i + 1, newTrie.size());
			trie = newTrie;
		}
	}

	@Test
	public void addAnElementMultipleTimesDoesNotIncreaseSize() throws Exception {
		Node<Object> trie = HashTries.empty();
		trie = trie.add(0,1,1).add(0,2,2).add(0,3,3).add(0,2,2);
		assertEquals("Size", 3, trie.size());
	}

	@Test
	public void containsOnNonExistingElement() throws Exception {
		Node<Object> trie = HashTries.empty();
		trie = trie.add(0,1,1).add(0,2,2).add(0,4,4);
		assertTrue("Expected to not contain 3", trie.get(3, 3).hasNoValue());
	}
}

