package com.jayway.changeless.vectors;



final class SuffixVector<T> extends VectorSupport<T>{

	private final Vector<T> vector;
	private final int offset;

	private SuffixVector(Vector<T> vector, int offset) {
		this.vector = vector;
		this.offset = offset;
	}
	
	public static <T> Vector<T> create(Vector<T> vector, int offset) {
		if (offset <= 0) {
			return vector;
		}
		if (vector.size() <= offset) {
			return Vectors.empty();
		}
		return new SuffixVector<T>(vector, offset);
	}
	
	@Override
	public boolean isEmpty() {
		return vector.isEmpty();
	}
	
	@Override
	public Vector<T> add(T... elements) {
		return create(vector.add(elements), offset);
	}

	@Override
	public Vector<T> skip(int n) {
		return create(vector, Math.max(this.offset + n, this.offset));
	}

	@Override
	public T get(int index) {
		EnsureValidIndex(index);
		return vector.get(offset + index);
	}

	@Override
	public int size() {
		return vector.size() - offset;
	}

	@Override
	public Vector<T> set(int index, T element) {
		EnsureValidIndex(index);
		return new SuffixVector<T>(vector.set(offset + index, element), offset);
	}
}
