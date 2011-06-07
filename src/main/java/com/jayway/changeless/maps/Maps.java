package com.jayway.changeless.maps;

import com.jayway.changeless.tuples.Tuple;

/**
 * An utility class for working with {@link Map} instances.
 */
public final class Maps {
	private Maps() { /* Static class */ }
	
	/**
	 * Creates an empty map.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @return the empty map.
	 */
	public static <K,V> Map<K, V> empty() {
		return ImmutableHashMap.empty();
	}
	
	/**
	 * Creates an empty map.
	 * @param <K> type of the keys in the map.
	 * @param <V> type of the values in the map.
	 * @return the empty map.
	 */
	public static <K,V> Map<K,V> of() {
		return empty();
	}
	
	public static <K,V> Map<K,V> of(K k0, V v0) {
		Map<K, V> result = empty();
		return result.put(k0, v0);
	}
	
	public static <K,V> Map<K,V> of(K k0, V v0, K k1, V v1) {
		Map<K, V> result = of(k0, v0);
		return result.put(k1, v1);
	}
	
	public static <K,V> Map<K,V> of(K k0, V v0, K k1, V v1, K k2, V v2) {
		Map<K, V> result = of(k0, v0, k1, v1);
		return result.put(k2, v2);
	}
	
	public static <K,V> Map<K,V> of(K k0, V v0, K k1, V v1, K k2, V v2, K k3, V v3) {
		Map<K, V> result = of(k0, v0, k1, v1, k2, v2);
		return result.put(k3, v3);
	}
	
	public static <K,V> Map<K,V> of(Iterable<Tuple<K, V>> elements) {
		Map<K, V> result = empty();
		for (Tuple<K,V> entry : elements) {
			result = result.put(entry.getFirst(), entry.getSecond());
		}
		return result;
	}
}
