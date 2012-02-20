package com.jayway.changeless.internal.searchtrees;

public final class SearchTrees {
	private SearchTrees() { /* Static class */ }

	public static <T extends Comparable<T>> SearchTree<T> empty() {
		return LeafNode.create();
	}
	
	public static <T extends Comparable<T>> SearchTree<T> of(T... elements) {
		SearchTree<T> result = LeafNode.create();
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}
}
