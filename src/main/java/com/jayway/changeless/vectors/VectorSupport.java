package com.jayway.changeless.vectors;

import java.util.Iterator;

import com.jayway.changeless.intervals.Intervals;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.utilities.Guard;

/**
 * A support class for easily implement {@link Vector}'s.
 * To implement your own {@link Vector} you need to implements 
 * the following methods: add, get, set, size.
 * 
 * @param <T> the type of the elements in this vector.
 */
public abstract class VectorSupport<T> implements Vector<T> {
	@Override
	public abstract Vector<T> add(T... elements);
	
	@Override
	public abstract T get(int index);

	@Override
	public abstract Vector<T> set(int index, T element);

	@Override
	public abstract int size();

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Vector<T> skip(int n) {
		return SuffixVector.create(this, n);
	}
	
	@Override
	public Vector<T> take(int n) {
		return PrefixVector.create(this, n);
	}

	@Override
	public Sequence<T> sequence() {
		return VectorSequence.create(this);
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}

	@Override
	public String toString() {
		return sequence().toString();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		
		if (o instanceof Vector) {
			Vector v = (Vector)o;
			if (size() != v.size()) {
				return false;
			} 
		}
		
		return sequence().equals(o);
	}

	@Override
	public int hashCode() {
		return sequence().hashCode();
	}
	
	protected void EnsureValidIndex(int index) {
		Guard.in(index, Intervals.zero.open(size()), "index");
	}
}
