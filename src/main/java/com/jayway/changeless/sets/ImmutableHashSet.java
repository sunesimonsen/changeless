package com.jayway.changeless.sets;

import java.util.Iterator;

import com.jayway.changeless.internal.hashtrie.EmptyNode;
import com.jayway.changeless.internal.hashtrie.Node;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;


final class ImmutableHashSet<T> implements Set<T> {

	private final Node<T> root;
	private int cachedHashcode = -1;
	
	boolean isHashCodeCached() {
		return cachedHashcode != -1;
	}
	
	private ImmutableHashSet(Node<T> root) {
		this.root = root;
	}
	
	public static <T> Set<T> empty() {
		return new ImmutableHashSet<T>(new EmptyNode<T>());
	}
	
	public static <T> Set<T> copyOf(Iterable<T> iterable) {
		Node<T> root = new EmptyNode<T>();
		for (T value : iterable) {
			root = root.add(0, value.hashCode(), value);
		}
		return new ImmutableHashSet<T>(root);
	}
	
	public static <T> Set<T> of(T... elements) {
		Node<T> root = new EmptyNode<T>();
		for (T value : elements) {
			root = root.add(0, value.hashCode(), value);
		}
		return new ImmutableHashSet<T>(root);
	}

	@Override
	public Set<T> add(T value) {
		return new ImmutableHashSet<T>(root.add(0, value.hashCode(), value));
	}

	@Override
	public boolean contains(T value) {
		Optional<T> seachResult = root.get(value, value.hashCode());
		return seachResult.hasValue();
	}

	@Override
	public int size() {
		return root.size();
	}

	@Override
	public Set<T> remove(T value) {
		return new ImmutableHashSet<T>(root.remove(value, value.hashCode()));
	}
	
	@Override
	public Set<T> remove(Set<T> values) {
		Node<T> newRoot = root;
		for (T value : values) {
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
	public Set<T> intersection(Set<T> set) {
		Set<T> intersection = Sets.empty();
		for (T value : set.sequence()) {
			if (contains(value)) {
				intersection = intersection.add(value);
			}
		}
		return intersection;
	}
	
	@Override
	public Set<T> union(Set<T> set) {
		Set<T> union = this;
		for (T value : set.sequence()) {
			union = union.add(value);
		}
		return union;
	}

	@Override
	public int hashCode() {
		if (isHashCodeCached()) {
			return cachedHashcode;
		}
		
		final int prime = 31;
		int result = 1;
		
		for (T element : sequence()) {
			result = prime * result + ((element == null) ? 0 : element.hashCode());	
		}
		cachedHashcode = result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ImmutableHashSet<T> other = (ImmutableHashSet<T>) obj;
		if (isHashCodeCached() && other.isHashCodeCached() &&
				hashCode() != other.hashCode()) {
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