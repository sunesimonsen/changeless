package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;


final class EmptyNode<T> implements Node<T> {

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
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}

	@Override
	public Sequence<T> sequence() {
		return Sequences.empty();
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}

	@Override
	public int waist() {
		return 0;
	}
}
