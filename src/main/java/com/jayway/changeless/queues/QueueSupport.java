package com.jayway.changeless.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.jayway.changeless.optionals.Optional;

public abstract class QueueSupport<T> implements Queue<T> {

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
	
	@Override
	public int hashCode() {
		return sequence().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return sequence().equals(obj);
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}
	

	@Override
	public Optional<T> poll() {
		if (isEmpty()) {
			return Optional.none();
		}
		return Optional.valueOf(peek());
	}
	
	protected void ensureNonEmpty(String message) {
		if (isEmpty()) {
			throw new NoSuchElementException(message);
		}
	}
}
