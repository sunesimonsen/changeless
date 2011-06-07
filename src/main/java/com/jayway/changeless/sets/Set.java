package com.jayway.changeless.sets;

import com.jayway.changeless.sequences.Sequenceable;

/**
 * An immutable implementation of the mathematical set abstraction.
 * A set is a collection of distinct objects in the sense that no pair 
 * of elements e1 and e2 exists in the set such that e1.equals(e2) is true.
 * @param <T> The type of the elements.
 */
public interface Set<T> extends Sequenceable<T> {
	/**
	 * Returns a new set that is the union of the elements in this set and the given elements. 
	 * @param elements the elements to be added.
	 * @return a new set that is the union of the elements in this set and the given elements.
	 */
	Set<T> add(T... elements);
	
	/**
	 * Returns true if all the given elements are members of this set.
	 * @param elements the elements that should be tested for membership.
	 * @return true if all the given elements are members of this set.; false otherwise.
	 */
	boolean contains(T... elements);
	
	/**
	 * Returns the number of elements in the set.
	 * @return the number of elements in the set.
	 */
	int size();
	
	/**
	 * Returns a new set with the elements of this set except the given elements.
	 * @param element the elements that should be excluded from the returned set.
	 * @return a new set with the elements of this set except the given elements.
	 */
	Set<T> remove(T... element);
	
	/**
	 * Returns a new set with the elements of this set except the given elements.
	 * @param elements the elements that should be excluded from the returned set.
	 * @return a new set with the elements of this set except the given elements.
	 */
	Set<T> remove(Iterable<T> elements);
	
	/**
	 * Returns true if the this set is empty.
	 * @return true if the this set is empty; false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Returns a new set containing the elements of this set and the set of given elements 
	 * except the elements that is present in both collections. This operation corresponds 
	 * to the symmetric difference of two sets.
	 * @param elements the elements.
	 * @return the set that is the symmetric difference of this set and the set of 
	 * the given elements.
	 */
	Set<T> symmetricDifference(Iterable<T> elements);
	
	/**
	 * Returns a new set containing the given elements that are members of this set.
	 * This operation corresponds to the intersection of two sets.
	 * @param elements the elements.
	 * @return the intersection of this set and the given elements.
	 */
	Set<T> intersection(Iterable<T> elements);
	
	/**
	 * Returns a new set containing elements of this set and the given elements.
	 * This operation corresponds to the union of two sets.
	 * @param elements the elements.
	 * @return the union of this set and the given elements.
	 */
	Set<T> union(Iterable<T> elements);
}

