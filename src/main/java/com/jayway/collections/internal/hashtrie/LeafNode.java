package com.jayway.collections.internal.hashtrie;

import java.util.Iterator;

import com.jayway.collections.Optional;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;

final class LeafNode<T> extends SingleNode<T> {
	private final T value;

	public LeafNode(int hash, T value) {
		super(hash);
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Node<T> add(int shift, int hash, T value) {
		if (this.value.equals(value)) {
			return this;
		} else if (getHash() == hash) {
			Sequence<T> bucket = Sequences.of(this.value, value);
			return new CollisionNode<T>(hash, bucket);
		} else {
			return bitmap(shift, hash, value);
		}
	}

	@Override
	public Optional<T> get(T value, int hash) {
		if (this.value.equals(value)) {
			return Optional.valueOf(this.value);
		}

		return Optional.none();
	}

	@Override
	public Node<T> remove(T value, int hash) {
		if (this.value.equals(value)) {
			return new EmptyNode<T>();
		}

		return this;
	}

	@Override
	public int size() {
		return 1;
	}
	
	

	@Override
	public String toString() {
		return sequence().toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> sequence() {
		return Sequences.of(value);
	}
	
	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}
