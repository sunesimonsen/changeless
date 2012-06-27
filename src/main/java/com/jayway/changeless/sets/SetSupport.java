package com.jayway.changeless.sets;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.sequences.Sequence;

public abstract class SetSupport<T> implements Set<T> {
	private volatile int cachedHashcode = -1;
	
	private boolean isHashCodeCached() {
		return cachedHashcode != -1;
	}
	
	@Override
	public int hashCode() {
		if (isHashCodeCached()) {
			return cachedHashcode;
		}
		
		cachedHashcode = sequence().hashCode();
		return cachedHashcode;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Set))
			return false;

		Set<T> other = (Set<T>) obj;
		if (hashCode() != other.hashCode()) {
			return false;
		}
		
		int size = 0;
		for (T value : other.sequence()) {
			if (!contains(value)) {
				return false;
			}
			size++;
		}
		
		return size() == size;
	}
	
	@Override
	public Set<T> remove(T... elements) {
		Set<T> result = this;
		for (T element : elements) {
			result = result.remove(element);
		}
		return result;
	}
    
	@Override
	public Set<T> remove(Iterable<T> elements) {
		Set<T> result = this;
		for (T element : elements) {
            result = result.remove(element);
		}
		return result;
	}
	
	@Override
	public boolean contains(T... elements) {
		for (T element : elements) {
			if (!contains(element)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public Set<T> add(T... elements) {
		Set<T> result = this;
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}
	
	@Override
	public Set<T> symmetricDifference(Iterable<T> elements) {
		Set<T> result = this;
		for (T element : elements) {
			if (result.contains(element)) {
				result = result.remove(element);
			} else {
				result = result.add(element);
			}
		}
		return result;
	}
	
	@Override
	public Set<T> union(Iterable<T> elements) {
		Set<T> result = this;
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}
	
	protected abstract Set<T> createEmptySet();
	
	@Override
	public Set<T> intersection(Iterable<T> elements) {
		Set<T> result = createEmptySet();
		for (T element : elements) {
			if (this.contains(element)) {
				result = result.add(element);
			}
		}
		return result;
	}

	@Override
	public <R> Sequence<R> transform(Fn<? super T, ? extends R> function) {
		return sequence().transform(function);
	}
}
