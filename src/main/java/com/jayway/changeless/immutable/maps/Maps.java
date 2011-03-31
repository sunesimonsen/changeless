package com.jayway.changeless.immutable.maps;

import com.jayway.changeless.tuples.Tuple;


public final class Maps {
	private Maps() {}
	
	public static <K,V> Map<K, V> empty() {
		return ImmutableHashMap.empty();
	}
	
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
