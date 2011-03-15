package com.jayway.collections.immutable.vectors;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.SequenceSupport;
import com.jayway.collections.utilities.Guard;

public class SuffixVector<T> extends SequenceSupport<T> implements Vector<T>{

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
	public Vector<T> add(T element) {
		return create(vector.add(element), offset);
	}
	
	@Override
	public Vector<T> add(T... elements) {
		return create(vector.add(elements), offset);
	}

	@Override
	public Sequence<T> rest() {
		return skip(1);
	}

	@Override
	public Vector<T> skip(int n) {
		Guard.nonNegative(offset, "offset");
		return create(vector, this.offset + n);
	}

	@Override
	public T first() {
		return vector.get(offset);
	}
}
