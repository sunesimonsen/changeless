package com.jayway.changeless.internal.hashtrie;

/**
 * A class containing convenient method for working with hash tries.
 */
public final class HashTries {
	private HashTries() { /* Static class */}
	/**
	 * Creates an empty hash trie.
	 * @param <T> the type of the elements in the created hash trie.s
	 * @return an empty hash trie.
	 */
	public static <T> HashTrie<T> empty() {
		return new EmptyNode<T>();
	}
}
