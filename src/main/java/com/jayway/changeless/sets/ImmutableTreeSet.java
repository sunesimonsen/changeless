package com.jayway.changeless.sets;

import java.util.Iterator;

import com.jayway.changeless.internal.searchtrees.SearchTree;
import com.jayway.changeless.internal.searchtrees.SearchTrees;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;


final class ImmutableTreeSet<T extends Comparable<T>> extends SetSupport<T> {

	private final SearchTree<T> root;
	
	
	private ImmutableTreeSet(SearchTree<T> root) {
		this.root = root;
	}
	
	public static <T extends Comparable<T>> Set<T> empty() {
		SearchTree<T> root = SearchTrees.empty();
		return new ImmutableTreeSet<T>(root);
	}

	@Override
	public Set<T> add(T element) {
		SearchTree<T> newRoot = root.add(element);
		return new ImmutableTreeSet<T>(newRoot);
	}

	@Override
	public boolean contains(T element) {
		Optional<T> seachResult = root.find(element);
		return seachResult.hasValue();
	}

	@Override
	public int size() {
		// Todo can be optimized
		return root.sequence().size();
	}

	@Override
	public Set<T> remove(T element) {
		SearchTree<T> newRoot = root.remove(element);
		return new ImmutableTreeSet<T>(newRoot);
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