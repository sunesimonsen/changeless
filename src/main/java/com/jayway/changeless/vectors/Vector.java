package com.jayway.changeless.vectors;

import com.jayway.changeless.sequences.Sequenceable;

public interface Vector<T> extends Sequenceable<T> {
	T get(int index);
	Vector<T> set(int index, T element);
	boolean isEmpty();
	int size();
	Vector<T> add(T... elements);
	/**
	 * Returns a new vector containing all the elements of this vector except 
	 * the n first elements. If n is less than one this vector is returned.
	 * 
	 * Notice that the returned sequence is evaluated lazily.
	 */
	Vector<T> skip(int n);
	Vector<T> take(int n);
}
