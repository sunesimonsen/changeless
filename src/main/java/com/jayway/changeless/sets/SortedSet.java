package com.jayway.changeless.sets;

/**
 * An immutable implementation of the mathematical set abstraction, where the 
 * set members are ordered according to the {@link Comparable} interface.
 * A set is a collection of distinct objects in the sense that no pair 
 * of elements e1 and e2 exists in the set such that e1.equals(e2) is true.
 * @param <T> The type of the elements.
 */
public interface SortedSet<T extends Comparable<T>> extends Set<T> {
	/**
	 * Returns a new sorted set that is the union of the elements in this set and the given element. 
	 * @param element the element to be added.
	 * @return a new set that is the union of the elements in this set and the given element.
	 */
	SortedSet<T> add(T element);
	
	/**
	 * Returns a new sorted set that is the union of the elements in this set and the given elements. 
	 * @param elements the elements to be added.
	 * @return a new set that is the union of the elements in this set and the given elements.
	 */
	SortedSet<T> add(T... elements);
	
	/**
	 * Returns a new sorted set with the elements of this set except the given element.
	 * @param element the element that should be excluded from the returned set.
	 * @return a new set with the elements of this set except the given element.
	 */
	SortedSet<T> remove(T element);
	
	/**
	 * Returns a new sorted set with the elements of this set except the given elements.
	 * @param elements the elements that should be excluded from the returned set.
	 * @return a new set with the elements of this set except the given elements.
	 */
	SortedSet<T> remove(T... elements);
	
	/**
	 * Returns a new sorted set with the elements of this set except the given elements.
	 * @param elements the elements that should be excluded from the returned set.
	 * @return a new set with the elements of this set except the given elements.
	 */
	SortedSet<T> remove(Iterable<T> elements);
	
	/**
	 * Returns a new sorted set containing the elements of this set and the set of given elements 
	 * except the elements that is present in both collections. This operation corresponds 
	 * to the symmetric difference of two sets.
	 * @param elements the elements.
	 * @return the set that is the symmetric difference of this set and the set of 
	 * the given elements.
	 */
	SortedSet<T> symmetricDifference(Iterable<T> elements);
	
	/**
	 * Returns a new sorted set containing the given elements that are members of this set.
	 * This operation corresponds to the intersection of two sets.
	 * @param elements the elements.
	 * @return the intersection of this set and the given elements.
	 */
	SortedSet<T> intersection(Iterable<T> elements);
	
	/**
	 * Returns a new sorted set containing elements of this set and the given elements.
	 * This operation corresponds to the union of two sets.
	 * @param elements the elements.
	 * @return the union of this set and the given elements.
	 */
	SortedSet<T> union(Iterable<T> elements);

	/**
	 * Return the smallest value in this set.
	 * @return the smallest value in this set.
	 * @throws NoSuchElementException if this set is empty.
	 */
	T min();
	
	/**
	 * Return the largest value in this set.
	 * @return the largest value in this set.
	 * @throws NoSuchElementException if this set is empty.
	 */
	T max();

	/**
	 * Returns a new sorted set with all the elements of this set 
	 * except the smallest element.
	 * @return a new sorted set with all the elements of this set 
	 * except the smallest element.
	 */
	SortedSet<T> removeMin();
	
	/**
	 * Returns a new sorted set with all the elements of this set 
	 * except the largest element.
	 * @return a new sorted set with all the elements of this set 
	 * except the largest element.
	 */
	SortedSet<T> removeMax();
}
