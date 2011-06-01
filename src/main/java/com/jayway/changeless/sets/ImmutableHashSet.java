package com.jayway.changeless.sets;

import java.util.Iterator;

import com.jayway.changeless.internal.hashtrie.HashTries;
import com.jayway.changeless.internal.hashtrie.HashTrie;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;


final class ImmutableHashSet<T> implements Set<T> {

	private final HashTrie<T> root;
	private volatile int cachedHashcode = -1;
	
	private boolean isHashCodeCached() {
		return cachedHashcode != -1;
	}
	
	private ImmutableHashSet(HashTrie<T> root) {
		this.root = root;
	}
	
	public static <T> Set<T> empty() {
		HashTrie<T> root = HashTries.empty();
		return new ImmutableHashSet<T>(root);
	}
	
	public static <T> Set<T> copyOf(Iterable<T> iterable) {
		HashTrie<T> root = HashTries.empty();
		for (T value : iterable) {
			root = root.add(0, value.hashCode(), value);
		}
		return new ImmutableHashSet<T>(root);
	}
	
	public static <T> Set<T> of(T... elements) {
		HashTrie<T> root = HashTries.empty();
		for (T value : elements) {
			root = root.add(0, value.hashCode(), value);
		}
		return new ImmutableHashSet<T>(root);
	}

	@Override
	public Set<T> add(T... elements) {
		HashTrie<T> newRoot = root;
		for (T element : elements) {
			newRoot = newRoot.add(0, element.hashCode(), element);
		}
		return new ImmutableHashSet<T>(newRoot);
	}

	@Override
	public boolean contains(T... elements) {
		for (T element : elements) {
			Optional<T> seachResult = root.get(element, element.hashCode());
			if (seachResult.hasNoValue()) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int size() {
		return root.size();
	}

	@Override
	public Set<T> remove(T... elements) {
		HashTrie<T> newRoot = root;
		for (T element : elements) {
			newRoot = newRoot.remove(element, element.hashCode());
		}
		return new ImmutableHashSet<T>(newRoot);
	}
	
	@Override
	public Set<T> remove(Iterable<T> elements) {
		HashTrie<T> newRoot = root;
		for (T value : elements) {
			newRoot = newRoot.remove(value, value.hashCode());
		}
		return new ImmutableHashSet<T>(newRoot);
	}

	@Override
	public Sequence<T> sequence() {
		return root.sequence();
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public Set<T> intersection(Iterable<T> elements) {
		HashTrie<T> newRoot = HashTries.empty();
		for (T element : elements) {
			int hash = element.hashCode();
			if (root.get(element, hash).hasValue()) {
				newRoot = newRoot.add(0, hash, element);
			}
		}
		return new ImmutableHashSet<T>(newRoot);
	}
	
	@Override
	public Set<T> union(Iterable<T> elements) {
		HashTrie<T> newRoot = root;
		for (T element : elements) {
			newRoot = newRoot.add(0, element.hashCode(), element);
		}
		return new ImmutableHashSet<T>(newRoot);
	}
	
	@Override
	public Set<T> symmetricDifference(Iterable<T> elements) {
		HashTrie<T> newRoot = root;
		for (T element : elements) {
			int hash = element.hashCode();
			if (newRoot.get(element, hash).hasValue()) {
				newRoot = newRoot.remove(element, hash);
			} else {
				newRoot = newRoot.add(0, hash, element);
			}
		}
		return new ImmutableHashSet<T>(newRoot);
	}

	@Override
	public int hashCode() {
		if (isHashCodeCached()) {
			return cachedHashcode;
		}
		
		final int prime = 31;
		int result = 1;
		
		for (T element : sequence()) {
			result = prime * result + element.hashCode();	
		}
		cachedHashcode = result;
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Set))
			return false;

		Set<T> other = (Set<T>) obj;
		if (hashCode() != other.hashCode()) {
			return false;
		}
		
		int size = 0;
		for (T value : other.sequence()) {
			if (!contains(value)) {
				return false;
			}
			size++;
		}
		
		return size() == size;
	}

	@Override
	public Iterator<T> iterator() {
		return root.iterator();
	}
}