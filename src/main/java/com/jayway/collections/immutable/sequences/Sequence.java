package com.jayway.collections.immutable.sequences;

import com.jayway.collections.functions.Fn;
import com.jayway.collections.functions.Fn2;
import com.jayway.collections.immutable.maps.Map;
import com.jayway.collections.predicates.Predicate;


public interface Sequence<T> extends Sequenceable<T> {
	/**
	 * Returns true if the sequence is empty; false otherwise.
	 * @return true if the sequence is empty; false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * @return the size of the sequence.
	 */
	int size();
	
	/**
	 * @return true if all the elements in the given sequence are equals to the elements in 
	 * this sequence.
	 */
	boolean equals(Object obj);
	
	/**
	 * Returns a new lazy sequence containing the elements from this sequence 
	 * except elements that are equals to the given element. 
	 * @param element The element to be removed.
	 * @return The resulting sequence.
	 */
	Sequence<T> remove(T element);
	
	/**
	 * Returns a new lazy sequence containing the elements from this sequence 
	 * except the elements matching the given predicate. 
	 * @param element The element to be removed.
	 * @return The resulting sequence.
	 */
	Sequence<T> remove(Predicate<? super T> predicate);
	
	/**
	 * Calculated that hash code based on all the elements hash codes.
	 * @return the hash code of the sequence.
	 */
	public int hashCode();
	/**
	 * Returns the first element of this sequence.
	 * @return the first element of this sequence.
	 * @throws IllegalStateException if the sequence is empty.
	 */
	T first();
	
	/**
	 * Returns a new sequence where the first element is removed.
	 * @return a new sequence where the first element is removed.
	 * @throws IllegalStateException if the sequence is empty.
	 */
	Sequence<T> rest();
	
	/**
	 * Returns a new sequence with all the given elements added to the front of this sequence.
	 * @param elements the elements to be added.
	 * @return a new sequence with all the given elements added to the front of this sequence.
	 * @throws IllegalArgumentException if any of the elements are null.
	 */
	Sequence<T> add(T... elements);

	/**
	 * Returns a new sequence with all the given elements added to the front of this sequence.
	 * @param elements the elements to be added.
	 * @return a new sequence with all the given elements added to the front of this sequence.
	 * @throws IllegalArgumentException if any of the elements are null.
	 */
	Sequence<T> add(Iterable<T> elements);
	
	/**
	 * Returns a new sequence where each element from this sequence is transformed with the given function. 
	 * 
	 * Notice that the returned sequence is produced lazily.
	 * 
	 * @param <R> the element type of the produced sequence.
	 * @param function the function used to transform the sequence. 
	 * @return a new sequence where each element from this sequence is transformed with the given function.
	 */
	<R> Sequence<R> transform(Fn<? super T,? extends R> function);

	/**
	 * Returns a new sequence containing all the elements that matches the given predicate 
	 * function. 
	 * 
	 * Notice that the returned sequence is evaluated lazily.
	 * 
	 * @param predicate the predicate.
	 * @return the filtered sequence.
	 */
	Sequence<T> filter(Predicate<? super T> predicate);
	
	/**
	 * Reduces this sequence into a accumulated result by calling the given 
	 * function on each element of the sequence starting with the provided start value.
	 * Reducing a sequence of the elements 1, 2, and 3 with the plus-function staring from zero, 
	 * will result in the following evaluation:
	 * <code>
	 * plus(plus(plus(0, 1), 2) 3)
	 * </code>
	 * @param <R> The type of the accumulated value. 
	 * @param start The base value for the calculation.
	 * @param function The accumulation function.
	 * @return The accumulated value.
	 */
	<R> R reduce(R start, Fn2<? super R, ? super T,R> function);
	
	/**
	 * Returns a new sequence containing all the elements of this sequence except 
	 * the n first elements. If n is less than one this sequence is returned.
	 * 
	 * Notice that the returned sequence is evaluated lazily.
	 */
	Sequence<T> skip(int n);
	
	/**
	 * Returns a new lazy sequence where this sequence is appended with the elements 
	 * of the given sequenceable.
	 * 
	 * @param sequenceable The sequenceable containing the elements to be appended.
	 * @return The appended sequence.
	 */
	Sequence<T> append(Sequenceable<T> sequenceable);
	
	/**
	 * Returns the reverse sequence.
	 * @return the reverse sequence.
	 */
	Sequence<T> reverse();

	/**
	 * Returns a map from distinct items in this sequence to the number of times
	 * they appear.
	 * @return the frequence map.
	 */
	Map<T, Integer> frequencies();
	
	
	
	/**
	 * Returns true if the predicate is true for all elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true is the predicate is true for all elements in the sequence; false otherwise.
	 */
	boolean all(Predicate<T> predicate);
	
	/**
	 * Returns true if the predicate is true for any elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true is the predicate is true for any elements in the sequence; false otherwise.
	 */
	boolean any(Predicate<T> predicate);
	
	/**
	 * Returns true if the predicate is not true for any elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true if the predicate is not true for any elements in the sequence; false otherwise.
	 */
	boolean non(Predicate<T> predicate);
	
	/**
	 * Returns the element with the given index in the this sequence.
	 * @param index The index of the element to return.
	 * @return the element at the given index.
	 * @throws IndexOutOfBoundsException if the sequence is shorter than the given index.
	 */
	T get(int index);

	/**
	 * @return a new sequence with n elements from this sequence.
	 */
	Sequence<T> take(int n);

	/**
	 * Returns a new lazy sequence with the elements of this sequence separated by
	 * the given separator.
	 * @param seperator The separator.
	 * @return lazy sequence of element in this sequence separated by
	 * the given separator.
	 */
	Sequence<T> interpose(T separator);
	
	/**
	 * Lazily partition the sequence into parts of size n.
	 * If the size of the sequence is not divisible by n the 
     * partition will include less elements.
	 * @param n The size of the partitions.
	 * @return a sequence of the partitions.
	 */
	Sequence<Sequence<T>> partition(int n);
}
