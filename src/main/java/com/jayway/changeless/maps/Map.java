package com.jayway.changeless.maps;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequenceable;
import com.jayway.changeless.tuples.Tuple;


public interface Map<K,V> extends Sequenceable<Tuple<K,V>> {
	Map<K, V> put(K key, V value);
	Optional<V> get(K key);
	V get(K key, V defaultValue);
	int size();
	boolean isEmpty();
}
