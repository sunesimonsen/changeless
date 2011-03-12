package com.jayway.collections.tuples;

import com.jayway.collections.utilities.Guard;

final class DefaultTuple<T1, T2> implements Tuple<T1, T2>{
	final T1 first;
	final T2 second;

	public DefaultTuple(T1 first, T2 second) {
		Guard.notNull(first, "first");
		Guard.notNull(second, "second");
		this.first = first;
		this.second = second;
	}

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}
	
	@Override
	public String toString() {
		return String.format("<%s,%s>", first,second);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + first.hashCode();
		result = prime * result + second.hashCode();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tuple))
			return false;
		
		Tuple other = (Tuple) obj;
		if (other.getFirst() == null)
			return false;
		if (other.getSecond() == null)
			return false;
		
		if (!first.equals(other.getFirst()))
			return false;
		
		if (!second.equals(other.getSecond()))
			return false;
		return true;
	}	
}
