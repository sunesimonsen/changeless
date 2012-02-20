package com.jayway.changeless.maps;

import com.jayway.changeless.utilities.Guard;

class MapEntry<K,V> {
	private final K key;
	private final V value;
	
	public MapEntry(K key) {
		this(key, null);
	}
	
	public MapEntry(K key, V value) {
		Guard.notNull(key,"key");
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("<%s,%s>", getKey(), getValue());
	}

	@Override
	public int hashCode() {
		return  key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		MapEntry other = (MapEntry) obj;
		return key.equals(other.key);
	}
}