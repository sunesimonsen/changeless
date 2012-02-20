package com.jayway.changeless.maps;

import com.jayway.changeless.internal.hashtrie.HashTrie;
import com.jayway.changeless.internal.hashtrie.HashTries;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.utilities.Guard;

final class ImmutableHashMap<K, V> extends MapSupport<K, V> {
	private final HashTrie<MapEntry<K, V>> root;
	
	private ImmutableHashMap(HashTrie<MapEntry<K, V>> root) {
		this.root = root;
	}
	
	public static <K,V> Map<K,V> empty() {
		return new ImmutableHashMap<K, V>(HashTries.<MapEntry<K, V>>empty());
	}
	
	@Override
	public Map<K, V> put(K key, V value) {
		Guard.notNull(value,"value");
		MapEntry<K,V> entry = new MapEntry<K, V>(key, value);
		int hashCode = entry.hashCode();
		// TODO this could be more efficient.
		return new ImmutableHashMap<K, V>(root.remove(entry, hashCode).add(0, hashCode, entry));
	}

	@Override
	public Optional<V> get(K key) {
		MapEntry<K,V> seachQuery = new MapEntry<K, V>(key);
		Optional<MapEntry<K, V>> result = root.get(seachQuery, seachQuery.hashCode());
		if (result.hasValue()) {
			return Optional.valueOf(result.getValue().getValue());
		}
		return Optional.none();
	}
	
	@Override
	public Map<K,V> remove(K key) {
		MapEntry<K, V> entry = new MapEntry<K, V>(key);
		return new ImmutableHashMap<K, V>(root.remove(entry, entry.hashCode()));
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int size() {
		return root.size();
	}

	@Override
	protected Sequence<MapEntry<K, V>> mapEntries() {
		return root.sequence();
	}
}