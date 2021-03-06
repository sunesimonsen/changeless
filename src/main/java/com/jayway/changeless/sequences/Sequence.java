package com.jayway.changeless.sequences;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.predicates.Predicate;
import com.jayway.changeless.sets.Set;
import com.jayway.changeless.tuples.Tuple;

/**
 * <p>
 * A ordered sequences of elements.
 * </p>
 * <p>
 * Notice that is you are going to implement you own sequence 
 * the Changeless library provides you with a couple of convenient
 * classes. See {@link SequenceSupport} and {@link LazySequence}.  
 * </p>
 * @param <T> the type of the elements in this sequence.
 */
public interface Sequence<T> extends Sequenceable<T> {
	/**
	 * Returns true if the sequence is empty; false otherwise.
	 * @return true if the sequence is empty; false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the sequence.
	 * @return the size of the sequence.
	 */
	int size();
	
	/**
	 * Returns true if the size of this sequence is equals to the given size.
	 * This is faster than using the size method.
	 * @param size the expected size.
	 * @return true if the size of this sequence is equals to the given size; false otherwise.
	 */
	boolean isSize(int size);
	
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
	Sequence<T> remove(Object element);
	
	/**
	 * Returns a new lazy sequence containing the elements from this sequence 
	 * except the elements matching the given predicate. 
	 * @param predicate the predicate function deciding which elements to remove.
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
	 * Returns a new sequence with the given element added to the front of this sequence.
	 * @param element the element to be added.
	 * @return a new sequence with the given element added to the front of this sequence.
	 * @throws IllegalArgumentException if the element is null.
	 */
	Sequence<T> add(T element);
	
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
	Sequence<T> add(Iterable<? extends T> elements);
	
	/**
	 * <p>
	 * Returns a new sequence where each element from this sequence is transformed with the given function. 
	 * </p>
	 * <p>
	 * Notice that the returned sequence is produced lazily.
	 * </p>
	 * @param <R> the element type of the produced sequence.
	 * @param function the function used to transform the sequence. 
	 * @return a new sequence where each element from this sequence is transformed with the given function.
	 */
	<R> Sequence<R> transform(Fn<? super T,? extends R> function);
	
	/**
	 * <p>
	 * Returns a new sequence where each element from this sequence is transformed with the given function. 
	 * </p>
	 * <p>
	 * The given function will be called for each element in the sequence. When the function is called 
	 * it's arguments will be the index of the element and the element. 
	 * </p>
	 * <p>
	 * Notice that the returned sequence is produced lazily.
	 * </p>
	 * @param <R> the element type of the produced sequence.
	 * @param function the function used to transform the sequence, taking the index of the element and 
	 * the element itself. 
	 * @return a new sequence where each element from this sequence is transformed with the given function.
	 */
	<R> Sequence<R> transformIndexed(Fn2<Integer, ? super T,? extends R> function);

	/**
	 * Returns a new lazy sequence containing all the elements that matches the given predicate 
	 * function.
	 * 
	 * @param predicate the predicate.
	 * @return the filtered sequence.
	 */
	Sequence<T> filter(Predicate<? super T> predicate);
	
	/**
	 * <p>
	 * Reduces this sequence into a accumulated result by calling the given 
	 * function on each element of the sequence starting with the provided start value.
	 * Reducing a sequence of the elements 1, 2, and 3 with the plus-function staring from zero, 
	 * will result in the following evaluation:
	 * </p>
	 * <p>
	 * <code>
	 * plus(plus(plus(0, 1), 2) 3)
	 * </code>
	 * </p>
	 * @param <R> The type of the accumulated value. 
	 * @param start The base value for the calculation.
	 * @param function The accumulation function.
	 * @return The accumulated value.
	 */
	<R> R reduce(R start, Fn2<? super R, ? super T,R> function);
	
	/**
	 * <p>
	 * Reduces this sequence into a accumulated result by calling the given 
	 * function on each element of the sequence starting with the first element.
	 * Reducing a sequence of the elements 0, 1, 2, and 3 with the plus-function, 
	 * will result in the following evaluation:
	 * </p>
	 * <p>
	 * <code>
	 * plus(plus(plus(0, 1), 2) 3)
	 * </code>
	 * </p>
	 * @param function The accumulation function.
	 * @return The accumulated value.
	 * @throws IllegalArgumentException if the sequence is empty.
	 */
	T reduce(Fn2<? super T, ? super T,T> function);
	
	/**
	 * Returns a new sequence containing the elements of this sequence except 
	 * the n first elements. If n is less than one this sequence is returned.
	 * @param n the number of elements to skip.
	 * @return a new sequence containing all the elements of this sequence except 
	 * the n first elements
	 */
	Sequence<T> skip(int n);
	
	/**
	 * Returns a new sequence containing the elements of this sequence except
	 * the prefix for which the elements matches the given predicate function.
	 * @param predicate the predicate deciding which elements to skip.
	 * @return a new sequence containing the elements of this sequence except
	 * the prefix for which the elements matches the given predicate function.
	 */
	Sequence<T> skipWhile(Predicate<T> predicate);
	
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
	 * @return the frequencies map.
	 */
	Map<T, Integer> frequencies();
	
	/**
	 * Returns true if the predicate is true for all elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true is the predicate is true for all elements in the sequence; false otherwise.
	 */
	boolean all(Predicate<? super T> predicate);
	
	/**
	 * Returns true if the predicate is true for any elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true is the predicate is true for any elements in the sequence; false otherwise.
	 */
	boolean any(Predicate<? super T> predicate);
	
	/**
	 * Returns true if the predicate is not true for any elements in the sequence.
	 * @param predicate The predicate function. 
	 * @return true if the predicate is not true for any elements in the sequence; false otherwise.
	 */
	boolean non(Predicate<? super T> predicate);
	
	/**
	 * Returns the element with the given index in the this sequence.
	 * @param index The index of the element to return.
	 * @return the element at the given index.
	 * @throws IndexOutOfBoundsException if the sequence is shorter than the given index.
	 */
	T get(int index);

	/**
	 * Returns a new lazy sequence with n elements from this sequence.
	 * @param n the number of element to take.
	 * @return a new lazy sequence with n elements from this sequence.
	 */
	Sequence<T> take(int n);
	
	/**
	 * Returns a new lazy sequence containing successive elements from this 
	 * sequence while the predicate returns true.
	 * @param predicate the predicate deciding how many elements to take.
	 * @return a new lazy sequence containing successive elements from this 
	 * sequence while the predicate returns true.
	 */
	Sequence<T> takeWhile(Predicate<T> predicate);

	/**
	 * Returns a new lazy sequence with the elements of this sequence separated by
	 * the given separator.
	 * @param <I> type of the separator.
	 * @param separator the separator.
	 * @return lazy sequence of element in this sequence separated by
	 * the given separator.
	 */
	<I extends T> Sequence<T> interpose(I separator);
	
	/**
	 * Lazily partition the sequence into parts of size n.
	 * If the size of the sequence is not divisible by n the 
	 * partition will include less elements.
	 * @param n The size of the partitions.
	 * @return a sequence of the partitions.
	 */
	Sequence<Sequence<T>> partition(int n);

	/**
	 * Returns an infinite sequences created by repeating this sequence.
	 * @return the created sequence.
	 */
	Sequence<T> cycle();

	/**
	 * <p>
	 * Zips the elements of this sequence and the given sequence into a lazy sequence of tuples.
	 * </p>
	 * <p>
	 * Example:
	 * <code>
	 * <pre>
	 * s1 = Sequences.of(1,2,3,4);
	 * s2 = Sequences.of(5,6,7,8,9,10);
	 * actual = s1.zip(s2);
	 * expected = Sequences.of(Tuples.of(1,5),
	 *                         Tuples.of(2,6),
	 *                         Tuples.of(3,7),
	 *                         Tuples.of(4,8));
	 * assertEquals(expected, actual);
	 * </pre>
	 * </code>
	 * </p>
	 * @param <TOther> the element type of the given sequence.
	 * @param sequence the sequence to be zipped together with this sequence.
	 * @return the zipped sequence.
	 */
	<TOther> Sequence<Tuple<T, TOther>> zip(Sequence<TOther> sequence);

	/**
	 * Returns a new sequence where the given elements are inserted at index in this sequence.
	 * If index is negative the given elements are inserted at the front of this sequence. If the 
	 * index is larger than the the size of this sequence the given elements are inserted at the 
	 * end of this sequence. 
	 * @param index the index the elements should be inserted at in this sequence.
	 * @param elements the elements to be inserted.
	 * @return the sequence with the elements inserted.
	 */
	Sequence<T> insertAt(int index, T... elements);

	/**
	 * Returns a new lazy sequence that contracts the elements for which their indexes 
	 * in the original sequence matches the given predicate. 
	 * @param predicate the predicate.
	 * @return a new lazy sequence containing the elements for which their indexes 
	 * in the original sequence matches the given predicate.
	 */
	Sequence<T> filterOnIndex(Predicate<? super Integer> predicate);
	
	/**
	 * Returns a new sequence with the first element of this sequence and the 
	 * given sequence as the rest of the elements.
	 * @param rest the rest of the elements in the sequence to return.
	 * @return a new sequence with the first element of this sequence and the 
	 * given sequence as the rest of the elements.
	 */
	Sequence<T> withRest(Sequence<T> rest);
	
	/**
	 * Returns the first elements that matches the given predicate function.
	 * @param predicate the predicate.
	 * @return the first elements that matches the given predicate function.
	 */
	Optional<T> find(Predicate<? super T> predicate);
	
	/**
	 * <p>
	 * Returns a new lazy sequence containing distinct elements from this 
	 * sequence. 
	 * </p>
	 * <p>
	 * Notice that because the resulting sequence is lazily calculated 
	 * a set of used elements will be maintained. This set will grow larger 
	 * as the sequence is traversed.
	 * </p>
	 * @return a new lazy sequence containing distinct elements from this 
	 * sequence.
	 */
	Sequence<T> distinct();
	
	// TODO add code examples
	/**
	 * <p>
	 * Returns a new sequence that is the result of sorting this sequence 
	 * by the values selected by the provided selector.
	 * </p>
	 * @param selector the selector that is used to select the values to 
	 * sort by.
	 * @return a sorted version of this sequence.
	 */
	<I extends Comparable<I>> Sequence<T> sortBy(Fn<? super T, I> selector);

	//TODO add code examples
	/**
	 * Returns a map containing the elements from this sequence group by the 
	 * values returned by the given selector.
	 * @param selector the selector deciding the value to group each element by.
	 * @return a map containing the elements from this sequence group by the 
	 * values returned by the given selector.
	 */
	<K> Map<K,Sequence<T>> groupBy(Fn<T, K> selector);
	
	/**
	 * Returns a new shuffled sequence containing the elements of this sequence 
	 * in random order.
	 * @return a new shuffled sequence containing the elements of this sequence 
	 * in random order.
	 */
	Sequence<T> shuffle();

	/**
	 * Returns a hash set of the elements in the sequence.
	 * @return a hash set of the elements in the sequence.
	 */
	Set<T> toSet();
}
