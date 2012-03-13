package com.jayway.changeless.sets;

import java.util.Iterator;

import com.jayway.changeless.internal.hashtrie.HashTrie;
import com.jayway.changeless.internal.hashtrie.HashTries;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;


final class ImmutableHashSet<T> extends SetSupport<T> {

	private final HashTrie<T> root;
	
	
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
	public Set<T> add(T element) {
		HashTrie<T> newRoot = root.add(0, element.hashCode(), element);
		return new ImmutableHashSet<T>(newRoot);
	}

	@Override
	public boolean contains(T element) {
		Optional<T> seachResult = root.get(element, element.hashCode());
		return seachResult.hasValue();
	}

	@Override
	public int size() {
		return root.size();
	}

	@Override
	public Set<T> remove(T element) {
		HashTrie<T> newRoot = root.remove(element, element.hashCode());
		return new ImmutableHashSet<T>(newRoot);
	}
	
	@Override
	public Sequence<T> sequence() {
		return root.sequence();
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}
	
	@Override
	public Iterator<T> iterator() {
		return root.iterator();
	}
	
	@Override
	public boolean matches(T input) {
		return contains(input);
	}

	@Override
	protected Set<T> createEmptySet() {
		return empty();
	}
}