package com.jayway.changeless.vectors;

import com.jayway.changeless.sequences.Sequenceable;

/**
 * A vector immutable collection where integer indexes maps to elements.
 * @param <T> the element type of the vector.
 */
public interface Vector<T> extends Sequenceable<T> {
	/**
	 * Returns the element at the given index in this vector.
	 * @param index the index of the element to be retrieved.
	 * @return the element at the given index in this vector.
	 * @throws IllegalArgumentException if the index is less than zero or 
	 * larger than or equals to the size of this vector.
	 */
	T get(int index);
	
	/**
	 * Returns a new vector where the element at the given index is set to 
	 * the specified element. 
	 * @param index the index of the element to be changed.
	 * @param element the element to be inserted in this vector.
	 * @return a new vector where the element at the given index is set to 
	 * the specified element.
	 * @throws IllegalArgumentException if the index is less than zero or 
	 * larger than or equals to the size of this vector.
	 */
	Vector<T> set(int index, T element);
	
	/**
	 * Returns true if this vector is empty; false otherwise.
	 * @return true if this vector is empty; false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in this vector.
	 * @return the number of elements in this vector.
	 */
	int size();
	
	/**
	 * Returns a new vector containing the elements of this vector with the 
	 * given elements appended to the end. 
	 * @param elements the elements to be added.
	 * @return this vector with the given elements added to the end.
	 */
	Vector<T> add(T... elements);
	
	/**
	 * Returns a new vector containing all the elements of this vector except 
	 * the n first elements. If n is less than one this vector is returned.
	 * 
	 * Notice that the returned sequence is evaluated lazily.
	 */
	Vector<T> skip(int n);
	
	/**
	 * Returns a new vector with the n first elements of this vector.
	 * @param n the number of elements to take from this vector.
	 * @return a new vector with the n first elements of this vector.
	 */
	Vector<T> take(int n);
}
