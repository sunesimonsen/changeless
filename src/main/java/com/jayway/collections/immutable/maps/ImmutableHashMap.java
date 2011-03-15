package com.jayway.collections.immutable.maps;

import java.util.Iterator;

import com.jayway.collections.Optional;
import com.jayway.collections.functions.Fn;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.internal.hashtrie.EmptyNode;
import com.jayway.collections.internal.hashtrie.Node;
import com.jayway.collections.tuples.Tuple;
import com.jayway.collections.tuples.Tuples;



class ImmutableHashMap<K, V> implements Map<K, V> {
	private final Node<MapEntry<K, V>> root;
	
	private ImmutableHashMap(Node<MapEntry<K, V>> root) {
		this.root = root;
	}
	
	public static <K,V> Map<K,V> empty() {
		return new ImmutableHashMap<K, V>(new EmptyNode<MapEntry<K, V>>());
	}
	
	@Override
	public Map<K, V> put(K key, V value) {
		MapEntry<K,V> entry = new MapEntry<K, V>(key, value);
		int hashCode = entry.hashCode();
		// TODO this could be more efficient.
		return new ImmutableHashMap<K, V>(root.remove(entry, hashCode).add(0, hashCode, entry));
	}

	@Override
	public Optional<V> get(K key) {
		MapEntry<K,V> seachQuery = new MapEntry<K, V>(key, null);
		Optional<MapEntry<K, V>> result = root.get(seachQuery, seachQuery.hashCode());
		if (result.hasValue()) {
			return Optional.valueOf(result.getValue().getValue());
		}
		return Optional.none();
	}
	
	@Override
	public V get(K key, V defaultValue) {
		Optional<V> result = get(key);
		if (result.hasNoValue()) {
			return defaultValue;
		} 
		return result.getValue();
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
	public String toString() {
		return sequence().toString();
	}

	@Override
	public Sequence<Tuple<K, V>> sequence() {
		return root.sequence().transform(new Fn<MapEntry<K, V>, Tuple<K,V>>() {
			@Override
			public Tuple<K, V> apply(MapEntry<K, V> input) {
				return Tuples.of(input.getKey(), input.getValue());
			}
		});
	}

	@Override
	public Iterator<Tuple<K, V>> iterator() {
		return sequence().iterator();
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		for (Tuple<K, V> mapEntry : this) {
			hashCode += mapEntry.getFirst().hashCode();
			hashCode += mapEntry.getSecond().hashCode();
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Map<?,?>))
			return false;
		
		int thisSize = size();
		int otherSize = 0;
		@SuppressWarnings("unchecked")	
		Map<K,V> other = (Map<K,V>) obj;
		for (Tuple<K, V> mapEntry : other) {
			Optional<V> result = get(mapEntry.getFirst());
			if (result.hasNoValue()) {
				return false;
			} else if (!result.getValue().equals(mapEntry.getSecond())) {
				return false;
			}
			otherSize++;
		}
		
		if (thisSize != otherSize) {
			return false;
		}
		
		return true;
	}
}