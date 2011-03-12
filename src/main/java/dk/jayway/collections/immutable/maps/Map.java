package dk.jayway.collections.immutable.maps;

import dk.jayway.collections.Optional;
import dk.jayway.collections.immutable.sequences.Sequenceable;

public interface Map<K,V> extends Sequenceable<MapEntry<K,V>> {
	Map<K, V> put(K key, V value);
	Optional<V> get(K key);
	V get(K key, V defaultValue);
	int size();
	boolean isEmpty();
}
