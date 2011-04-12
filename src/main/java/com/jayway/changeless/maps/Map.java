package com.jayway.changeless.maps;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequenceable;
import com.jayway.changeless.tuples.Tuple;

/**
 * A map is an immutable data structure that uniquely maps keys to an associated values.
 *
 * @param <K> the type of the keys in the map.
 * @param <V> the type of the values in the map. 
 */
public interface Map<K,V> extends Sequenceable<Tuple<K,V>> {
	/**
	 * Returns a new map where the given key is associated to the specified value. 
	 * @param key the key.
	 * @param value the value.
	 * @return a new map where the given key is associated to the specified value.
	 */
	Map<K, V> put(K key, V value);
	
	/**
	 * Returns an optional value retrieved by looking up the key in this map. If the 
	 * key is not present in this map none will be returned.
	 * @param key the key to lookup.
	 * @return an optional value retrieved by looking up the key. None is returned 
	 * is the key was not found. 
	 */
	Optional<V> get(K key);
	
	/**
	 * Returns the value retrieved by looking up the key in this map. If the 
	 * key is not present in this map the given default value will be returned. 
	 * @param key the key to lookup.
	 * @param defaultValue the default value that will be returned if the key is not found.
	 * @return the value retrieved by looking up the key in this map. If the 
	 * key is not present in this map the given default value will be returned.
	 */
	V get(K key, V defaultValue);
	
	/**
	 * Returns the number of keys in this map. 
	 * @return the number of keys in this map.
	 */
	int size();
	
	/**
	 * Returns true if this map contains no keys.
	 * @return true if this map contains no keys; false otherwise.
	 */
	boolean isEmpty();
}
