package com.jayway.collections.immutable.maps;

import com.jayway.collections.Optional;
import com.jayway.collections.immutable.sequences.Sequenceable;
import com.jayway.collections.tuples.Tuple;


public interface Map<K,V> extends Sequenceable<Tuple<K,V>> {
	Map<K, V> put(K key, V value);
	Optional<V> get(K key);
	V get(K key, V defaultValue);
	int size();
	boolean isEmpty();
}
