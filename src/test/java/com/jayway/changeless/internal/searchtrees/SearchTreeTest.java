package com.jayway.changeless.internal.searchtrees;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

public class SearchTreeTest {
	@Test
	public void emptyTreesAreEmpty() throws Exception {
		SearchTree<Integer> tree = SearchTrees.empty();
		assertThat("Should be empty", tree.isEmpty(), is(true));
	}
	
	@Test
	public void afterInsertingInEmptyTreeIsNotEmpty() throws Exception {
		SearchTree<Integer> emptyTree = SearchTrees.empty();
		SearchTree<Integer> tree = emptyTree.add(13);
		assertThat("Should not be empty", tree.isEmpty(), is(false));
		assertThat("Expected tree to contain element", tree.contains(13), is(true));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnAddNull() throws Exception {
		SearchTree<Integer> tree = SearchTrees.empty();
		tree.add(null);
	}
	
	@Test
	public void monkeyTest() throws Exception {
		Random random = new Random();
		Set<Integer> inserted = new HashSet<Integer>();
		SearchTree<Integer> tree = SearchTrees.empty();
		for (int i = 0; i < 1000; i++) {
			int value = random.nextInt(10000);
			tree = tree.add(value);
			inserted.add(value);
		}
		
		for (Integer value : inserted) {
			assertContains(tree, value);
			tree = tree.remove(value);
			assertNotContains(tree, value);
			assertValidInvariant(tree);
		}
	}
	
	@Test
	public void serialMonkeyTest() throws Exception {
		List<Integer> inserted = new ArrayList<Integer>();
		SearchTree<Integer> tree = SearchTrees.empty();
		for (int i = 0; i < 1000; i++) {
			tree = tree.add(i);
			inserted.add(i);
			assertValidInvariant(tree);
		}
		
		for (Integer value : inserted) {
			assertContains(tree, value);
			tree = tree.remove(value);
			assertNotContains(tree, value);
			assertValidInvariant(tree);
		}
	}
	
	@Test
	public void emptyTreeSequenceIsEmpty() throws Exception {
		SearchTree<Integer> actual = SearchTrees.empty();
		assertTrue("Expected empty sequence", actual.sequence().isEmpty());
	}
	
	@Test
	public void sequenceIsOrdered() throws Exception {
		Sequence<Integer> actual = SearchTrees.of(9,5,8,0,4,7,3,6,2,1).sequence();
		Sequence<Integer> expected = Sequences.from(0).upward().take(10);
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void toStringOnEmptyTree() throws Exception {
		SearchTree<String> tree = SearchTrees.empty();
		String actual = tree.toString();
		String expected = Sequences.empty().toString();
		assertEquals("Expected string", expected , actual);
	}
	
	@Test
	public void toStringOnNonEmptyTree() throws Exception {
		String actual = SearchTrees.of(9,5,8,0,4,7,3,6,2,1).toString();
		String expected = Sequences.from(0).upward().take(10).toString();
		assertEquals("Expected string", expected , actual);
	}
	
	private void assertValidInvariant(SearchTree<Integer> tree) {
		Node<Integer> root = (Node<Integer>) tree;
		root.ensureInvariant();
	}

	private static <T extends Comparable<T>> void assertContains(SearchTree<T> tree, T element) {
		if (!tree.contains(element)) {
			fail(String.format("Expected tree '%s' to contain element '%s'", tree, element));
		}
	}
	
	private static <T extends Comparable<T>> void assertNotContains(SearchTree<T> tree, T element) {
		if (tree.contains(element)) {
			fail(String.format("Did not expect tree '%s' to contain element '%s'", tree, element));
		}
	}
}
