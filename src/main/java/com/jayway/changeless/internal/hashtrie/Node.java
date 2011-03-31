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

import com.jayway.changeless.Optional;
import com.jayway.changeless.sequences.Sequenceable;


public interface Node<T> extends Sequenceable<T> {
	int size();
	boolean isEmpty();
	Node<T> add(int shift, int hash, T value);
	Optional<T> get(T value, int hash);
	Node<T> remove(T value, int hash);
}








