package com.jayway.changeless.maps;

import com.jayway.changeless.tuples.Tuple;

/**
 * An utility class for working with sorted {@link Map} instances.
 */
public final class SortedMaps {
	private SortedMaps() { /* Static class */ }
	
	/**
	 * Creates an empty map.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @return the empty map.
	 */
	public static <K extends Comparable<K>,V> Map<K, V> empty() {
		return ImmutableTreeMap.empty();
	}
	
	/**
	 * Creates an empty map.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @return the empty map.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> of() {
		return empty();
	}
	/**
	 * Create a new map containing the given keys and values.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @param k0 key 0
	 * @param v0 value 0 
	 * @return a new map containing the given keys and values.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> of(K k0, V v0) {
		Map<K, V> result = empty();
		return result.put(k0, v0);
	}
	
	/**
	 * Create a new map containing the given keys and values.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @param k0 key 0
	 * @param v0 value 0
	 * @param k1 key 1
	 * @param v1 value 1
	 * @return a new map containing the given keys and values.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> of(K k0, V v0, K k1, V v1) {
		Map<K, V> result = of(k0, v0);
		return result.put(k1, v1);
	}
	
	/**
	 * Create a new map containing the given keys and values.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @param k0 key 0
	 * @param v0 value 0
	 * @param k1 key 1
	 * @param v1 value 1
	 * @param k2 key 2
	 * @param v2 value 2
	 * @return a new map containing the given keys and values.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> of(K k0, V v0, K k1, V v1, K k2, V v2) {
		Map<K, V> result = of(k0, v0, k1, v1);
		return result.put(k2, v2);
	}
	
	/**
	 * Create a new map containing the given keys and values.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @param k0 key 0
	 * @param v0 value 0
	 * @param k1 key 1
	 * @param v1 value 1
	 * @param k2 key 2
	 * @param v2 value 2
	 * @param k3 key 3
	 * @param v3 value 3
	 * @return a new map containing the given keys and values.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> of(K k0, V v0, K k1, V v1, K k2, V v2, K k3, V v3) {
		Map<K, V> result = of(k0, v0, k1, v1, k2, v2);
		return result.put(k3, v3);
	}
	
	/**
	 * Create a new map containing the values of the given {@link Iterable}.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @param elements the key-value-pairs that the new map should contain. 
	 * @return a new map containing the values of the given {@link Iterable}.
	 */
	public static <K extends Comparable<K>,V> Map<K,V> copyOf(Iterable<Tuple<K, V>> elements) {
		Map<K, V> result = empty();
		for (Tuple<K,V> entry : elements) {
			result = result.put(entry.getFirst(), entry.getSecond());
		}
		return result;
	}
}
