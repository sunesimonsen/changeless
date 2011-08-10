package com.jayway.changeless.internal.searchtrees;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

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
		List<Integer> inserted = new ArrayList<Integer>();
		SearchTree<Integer> tree = SearchTrees.empty();
		for (int i = 0; i < 1000; i++) {
			int value = random.nextInt(10000);
			tree = tree.add(value);
			inserted.add(value);
		}
		
		for (Integer value : inserted) {
			assertContains(tree, value);
		}
		
		Node<Integer> root = (Node<Integer>) tree;
		root.ensureInvariant();
	}
	
	private static <T extends Comparable<T>> void assertContains(SearchTree<T> tree, T element) {
		if (!tree.contains(element)) {
			fail(String.format("Expected tree '%s' to contain element '%s'", tree, element));
		}
	}
}
