package com.jayway.changeless.maps;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.predicates.Predicate;
import com.jayway.changeless.sequences.Sequenceable;
import com.jayway.changeless.tuples.Tuple;

/**
 * A map is an immutable data structure that uniquely maps keys to an associated values.
 *
 * @param <K> the type of the keys in the map.
 * @param <V> the type of the values in the map. 
 */
public interface Map<K,V> extends Sequenceable<Tuple<K,V>>, Fn<K, Optional<V>>, Predicate<K> {
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
	 * Returns a new maps based on this map with the association identified by the given key removed.
	 * @param key the key identifying the association to remove.
	 * @return a new updated map.
	 */
	Map<K,V> remove(K key);
	
	/**
	 * Returns a new maps based on this map with the associations identified by the given keys removed.
	 * @param keys the keys identifying the associations to remove.
	 * @return a new updated map.
	 */
	Map<K,V> remove(K... keys);

	/**
	 * Returns a new map based on this maps but with the association 
	 * identified by the given key updated by applying the provided 
	 * function to the value.
	 * @param key the identifying the association to update.
	 * @param function the function used to update the value in the map.
	 * If there is no value for the supplied key an Option.none() is given as 
	 * argument to the provided function.
	 * @return the new updated map.
	 */
	Map<K,V> update(K key, Fn<Optional<V>,V> function);
	
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
	
	/**
	 * Returns true if maps contains the given key.
	 * @param key the key.
	 * @return true if maps contains the given key; false otherwise.
	 */
	boolean contains(K key);
}
