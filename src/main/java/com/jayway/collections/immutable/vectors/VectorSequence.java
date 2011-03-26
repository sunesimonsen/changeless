package com.jayway.collections.immutable.vectors;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.SequenceSupport;
import com.jayway.collections.immutable.sequences.Sequences;

final class VectorSequence<T> extends SequenceSupport<T> {
	private final Vector<T> vector;

	private VectorSequence(Vector<T> vector) {
		this.vector = vector;
	}
	
	public static <T> Sequence<T> create(Vector<T> vector) {
		if (vector.isEmpty()) {
			return Sequences.empty();
		}
		return new VectorSequence<T>(vector);
	}

	@Override
	public Sequence<T> rest() {
		return create(vector.skip(1));
	}
	
	@Override
	public Sequence<T> skip(int n) {
		return create(vector.skip(n));
	}

	@Override
	public T first() {
		return vector.get(0);
	}
}
