/**
 * This is an implementation of an immutable hash-trie is based on Daniel Spiewak
 * HashTrie implementation for Scala that is in turn a clean-room port of 
 * Rich Hickey's persistent hash trie implementation from Clojure 
 * (http://clojure.org). Originally presented as a mutable structure in a paper 
 * by Phil Bagwell. The implementation was also heavily inspired by Eugene 
 * Vigdorchik's blog post: 
 * http://groovy.dzone.com/articles/immutable-data-structures
 * 
 * @author Rich Hickey
 * @author Daniel Spiewak
 * @author Sune Simonsen
 */

package com.jayway.changeless.internal.hashtrie;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequenceable;

/**
 * An interface for hash tries.
 * @param <T> the type of the elements in this hash trie.
 */
public interface HashTrie<T> extends Sequenceable<T> {
	/**
	 * Returns the number of elements in this hash trie.
	 * @return the number of elements in this hash trie.
	 */
	int size();
	/**
	 * Returns true if this hash trie is empty; false otherwise.
	 * @return true if this hash trie is empty; false otherwise.
	 */
	boolean isEmpty();
	HashTrie<T> add(int shift, int hash, T value);
	Optional<T> get(T value, int hash);
	HashTrie<T> remove(T value, int hash);
}








