package com.jayway.changeless.maps;


final class SortedMapEntry<K extends Comparable<K>,V> extends MapEntry<K,V> implements Comparable<SortedMapEntry<K,V>> {
	public SortedMapEntry(K key) {
		super(key);
	}
	
	public SortedMapEntry(K key, V value) {
		super(key,value);
	}

	@Override
	public int compareTo(SortedMapEntry<K,V> other) {
		return getKey().compareTo(other.getKey());
	}
}