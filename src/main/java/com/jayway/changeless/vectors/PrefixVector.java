package com.jayway.changeless.vectors;

import com.jayway.changeless.intervals.Intervals;
import com.jayway.changeless.utilities.Guard;

final class PrefixVector<T> extends VectorSupport<T> {
	private final int size;
	private final Vector<T> vector;

	public PrefixVector(Vector<T> vector, int size) {
		this.vector = vector;
		this.size = size;
	}

	public static <T> Vector<T> create(Vector<T> vector, int size) {
		if (size <= 0) {
			return Vectors.empty();
		}
		
		if (vector.size() <= size) {
			return vector;
		}
		
		return new PrefixVector<T>(vector, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<T> add(T... elements) {
		Vector<T> result = vector;
		int originalSize = result.size();
		int i = 0;
		for (i = 0; i < elements.length && i < originalSize - size; i++) {
			result = result.set(size + i, elements[i]);
		}
		for (int j = i; j < elements.length; j++) {
			result = result.add(elements[j]);
		}
		
		return result;
	}

	@Override
	public T get(int index) {
		Guard.in(index, Intervals.zero.open(size()), "index");
		return vector.get(index);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Vector<T> set(int index, T element) {
		Guard.in(index, Intervals.zero.open(size()), "index");
		return new PrefixVector<T>(vector.set(index, element), size());
	}
}
