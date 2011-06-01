package com.jayway.changeless.internal.hashtrie;

/**
 * A class containing convenient method for working with {@link HashTrie}.
 */
public final class HashTries {
	private HashTries() { /* Static class */}
	/**
	 * Creates an empty {@link HashTrie}.
	 * @param <T> the type of the elements in the created {@link HashTrie}.
	 * @return an empty {@link HashTrie}.
	 */
	public static <T> HashTrie<T> empty() {
		return new EmptyNode<T>();
	}
}
