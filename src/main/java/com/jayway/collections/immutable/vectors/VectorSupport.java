package com.jayway.collections.immutable.vectors;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.SequenceSupport;

public abstract class VectorSupport<T> extends SequenceSupport<T> implements Vector<T> {
	@Override
	public T first() {
		return get(0);
	}
	
	@Override
	public Sequence<T> rest() {
		return skip(1);
	}
	
	@Override
	public Vector<T> skip(int n) {
		return SuffixVector.create(this, n);
	}

	@Override
	public abstract Vector<T> add(T element);
	
	@Override
	public abstract Vector<T> add(T... elements);
}
