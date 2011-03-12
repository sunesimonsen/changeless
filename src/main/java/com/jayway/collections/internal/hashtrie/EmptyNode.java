package com.jayway.collections.internal.hashtrie;

import java.util.Iterator;

import com.jayway.collections.Optional;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;


public class EmptyNode<T> implements Node<T> {

	@Override
	public Node<T> add(int shift, int hash, T value) {
		return new LeafNode<T>(hash, value);
	}

	@Override
	public Optional<T> get(T key, int hash) {
		return Optional.none();
	}

	@Override
	public Node<T> remove(T value, int hash) {
		return this;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Sequence<T> sequence() {
		return Sequences.empty();
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}
