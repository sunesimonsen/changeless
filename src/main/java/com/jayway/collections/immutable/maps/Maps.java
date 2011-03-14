package com.jayway.collections.immutable.maps;


public class Maps {
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
}
