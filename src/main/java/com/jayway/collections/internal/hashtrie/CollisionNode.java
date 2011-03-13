package com.jayway.collections.internal.hashtrie;

import java.util.Iterator;

import com.jayway.collections.Optional;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.predicates.Predicate;

class CollisionNode<T> extends SingleNode<T> {

	private final Sequence<T> bucket;

	public CollisionNode(int hash, Sequence<T> bucket) {
		super(hash);
		this.bucket = bucket;
	}

	@Override
	public Node<T> add(int shift, int hash, T value) {
		if (getHash() == hash) {

			// TODO use remove
			Sequence<T> newBucket = bucket.filter(
					new NotEqual<T>(value)).add(value);

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
		Sequence<T> newBucket = bucket.filter(
				new NotEqual<T>(value));

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

class NotEqual<T> implements Predicate<T> {

	private final T value;

	public NotEqual(T value) {
		this.value = value;
	}

	@Override
	public boolean apply(T input) {
		return !value.equals(input);
	}

}
