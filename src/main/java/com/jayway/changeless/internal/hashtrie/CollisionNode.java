package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;

final class CollisionNode<T> extends SingleNode<T> {

	private final Sequence<T> bucket;

	public CollisionNode(int hash, Sequence<T> bucket) {
		super(hash);
		this.bucket = bucket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Node<T> add(int shift, int hash, T value) {
		if (getHash() == hash) {
			Sequence<T> newBucket = bucket.remove(value).add(value);
			return new CollisionNode<T>(hash, newBucket);
		} else {
			return bitmap(shift, hash, value);
		}
	}

	@Override
	public Optional<T> get(T value, int hash) {
		for (T element : bucket) {
			if (element.equals(value)) {
				return Optional.valueOf(element);
			}
		}
		return Optional.none();
	}

	@Override
	public Node<T> remove(T value, int hash) {
		Sequence<T> newBucket = bucket.remove(value);

		int newBucketSize = newBucket.size();
		if (newBucketSize == bucket.size()) {
			return this;
		} else {
			if (newBucketSize == 1) {
				T first = newBucket.first();
				return new LeafNode<T>(hash, first);
			} else {
				return new CollisionNode<T>(hash, newBucket);
			}
		}
	}

	@Override
	public int size() {
		return bucket.size();
	}

	@Override
	public String toString() {
		return sequence().toString();
	}
	
	@Override
	public Sequence<T> sequence() {
		return bucket;
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}
