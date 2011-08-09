package com.jayway.changeless.internal.searchtrees;

public final class SearchTrees {
	private SearchTrees() { /* Static class */ }

	public static <T extends Comparable<T>> SearchTree<T> empty() {
		return EmptyNode.create();
	}
}
