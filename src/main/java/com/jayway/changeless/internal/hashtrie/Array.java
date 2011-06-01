package com.jayway.changeless.internal.hashtrie;

import java.util.Arrays;
import java.util.Iterator;

final class Array<T> implements Iterable<T> {
	private final Object[] data;
	
	private Array(Object[] data) {
		this.data = data;
	}
	
	public Array(int size) {
		data = new Object[size];
	}
	
	@SuppressWarnings("unchecked")
	public T get(int i) {
		return (T) data[i];
	}
	
	public void set(int i, T value) {
		data[i] = value;
	}
	
	public Array<T> copy() {
		return copy(data.length);
	}
	
	public Array<T> copy(int newSize) {
		return new Array<T>(Arrays.copyOf(data, newSize));
	}
	
	public int size() {
		return data.length;
	}

	@Override
	public Iterator<T> iterator() {
		return new SkipNullIterator();
	}
	
	private class SkipNullIterator implements Iterator<T> {
		private int i = 0;

		@Override
		public boolean hasNext() {
			while(i < data.length) {
				if (data[i] != null) {
					return true;
				}
				i++;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			return (T) data[i++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
