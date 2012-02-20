package com.jayway.changeless.maps;

import java.util.Iterator;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.tuples.Tuples;
import com.jayway.changeless.utilities.Guard;


abstract class MapSupport<K, V> implements Map<K, V>{
	@Override
	public String toString() {
		return sequence().toString();
	}
	
	protected abstract Sequence<? extends MapEntry<K, V>> mapEntries();
	
	@Override
	public Sequence<Tuple<K, V>> sequence() {
		return mapEntries().transform(new Fn<MapEntry<K, V>, Tuple<K,V>>() {
			@Override
			public Tuple<K, V> apply(MapEntry<K, V> input) {
				return Tuples.of(input.getKey(), input.getValue());
			}
		});
	}
	
	@Override
	public Map<K,V> update(K key, Fn<Optional<V>,V> function) {
		Optional<V> value = get(key);
		V updateValue = function.apply(value);
		return put(key, updateValue);
	}
	
	@Override
	public Iterator<Tuple<K, V>> iterator() {
		return sequence().iterator();
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (Tuple<K, V> mapEntry : this) {
			hashCode += mapEntry.getFirst().hashCode();
			hashCode += mapEntry.getSecond().hashCode();
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Map<?,?>))
			return false;
		
		int thisSize = size();
		int otherSize = 0;
		@SuppressWarnings("unchecked")	
		Map<K,V> other = (Map<K,V>) obj;
		for (Tuple<K, V> mapEntry : other) {
			Optional<V> result = get(mapEntry.getFirst());
			if (result.hasNoValue()) {
				return false;
			} else if (!result.getValue().equals(mapEntry.getSecond())) {
				return false;
			}
			otherSize++;
		}
		
		if (thisSize != otherSize) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public Optional<V> apply(K input) {
		return get(input);
	}

	@Override
	public boolean contains(K key) {
		return get(key).hasValue();
	}
	
	@Override
	public boolean matches(K input) {
		return contains(input);
	}
	
	@Override
	public Map<K,V> remove(K... keys) {
		Map<K, V> result = this;
		for (K k : keys) {
			result = result.remove(k);
		}
		return result;
	}
	
	@Override
	public Map<K, V> merge(Map<K, V> updates) {
		Guard.notNull(updates, "updates");
		Map<K, V> updated = this;
		for (Tuple<K, V> entry : updates) {
			updated = updated.put(entry.getFirst(), entry.getSecond());
		}
		return updated;
	}
	
	@Override
	public V get(K key, V defaultValue) {
		Optional<V> result = get(key);
		if (result.hasNoValue()) {
			return defaultValue;
		} 
		return result.getValue();
	}
}
