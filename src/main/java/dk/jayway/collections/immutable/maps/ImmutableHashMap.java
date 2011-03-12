package dk.jayway.collections.immutable.maps;

import java.util.Iterator;

import dk.jayway.collections.Optional;
import dk.jayway.collections.immutable.sequences.Sequence;
import dk.jayway.collections.internal.hashtrie.EmptyNode;
import dk.jayway.collections.internal.hashtrie.Node;


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
		return size() == 0;
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
	public Sequence<MapEntry<K, V>> sequence() {
		return root.sequence();
	}

	@Override
	public Iterator<MapEntry<K, V>> iterator() {
		return sequence().iterator();
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		for (MapEntry<K, V> mapEntry : this) {
			hashCode += mapEntry.getKey().hashCode();
			hashCode += mapEntry.getValue().hashCode();
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
		for (MapEntry<K, V> mapEntry : other) {
			Optional<V> result = get(mapEntry.getKey());
			if (result.hasNoValue()) {
				return false;
			} else if (!result.getValue().equals(mapEntry.getValue())) {
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