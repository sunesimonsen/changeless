package com.jayway.changeless.maps;

import com.jayway.changeless.internal.searchtrees.SearchTree;
import com.jayway.changeless.internal.searchtrees.SearchTrees;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.utilities.Guard;

public class ImmutableTreeMap<K extends Comparable<K>, V> extends MapSupport<K, V> {

	private final SearchTree<SortedMapEntry<K, V>> root;

	private ImmutableTreeMap(SearchTree<SortedMapEntry<K,V>> root) {
		this.root = root;
	}
	
	private static <K extends Comparable<K>,V> Map<K,V> create(SearchTree<SortedMapEntry<K,V>> root) {
		return new ImmutableTreeMap<K, V>(root);
	}
	
	public static <K extends Comparable<K>,V> Map<K,V> empty() {
		return create(SearchTrees.<SortedMapEntry<K, V>>empty());
	}
	
	@Override
	public Map<K, V> put(K key, V value) {
		Guard.notNull(value, "value");
		return create(root.add(new SortedMapEntry<K, V>(key, value)));
	}

	@Override
	public Optional<V> get(K key) {
		Optional<SortedMapEntry<K, V>> result = root.find(new SortedMapEntry<K, V>(key));
		if (result.hasValue()) {
			return Optional.valueOf(result.getValue().getValue());
		}
		return Optional.none();
	}

	@Override
	public Map<K, V> remove(K key) {
		return create(root.remove(new SortedMapEntry<K, V>(key)));
	}

	@Override
	public int size() {
		// Todo can be optimized
		return root.sequence().size();
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	protected Sequence<SortedMapEntry<K, V>> mapEntries() {
		return root.sequence();
	}
}
