package com.jayway.changeless.vectors;

import java.util.Iterator;

import com.jayway.changeless.intervals.Intervals;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequenceable;
import com.jayway.changeless.utilities.Guard;


public abstract class VectorSupport<T> implements Vector<T> {
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
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o instanceof Vector) {
			Vector v = (Vector)o;
			if (size() != v.size()) {
				return false;
			} 
			return sequence().equals(v.sequence());
		}
		
		if (!(o instanceof Sequenceable)) return false;
		return sequence().equals((Sequenceable)o);
	}

	@Override
	public int hashCode() {
		return sequence().hashCode();
	}
	
	@Override
	public abstract Vector<T> add(T... elements);
	
	protected void EnsureValidIndex(int index) {
		Guard.in(index, Intervals.zero.open(size()), "index");
	}
}
