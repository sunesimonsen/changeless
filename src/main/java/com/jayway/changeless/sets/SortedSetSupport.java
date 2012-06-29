package com.jayway.changeless.sets;

public abstract class SortedSetSupport<T extends Comparable<T>> extends SetSupport<T> implements SortedSet<T> {
	@Override
	public SortedSet<T> remove(T... elements) {
		return (SortedSet<T>) super.remove(elements);
	}
    
	@Override
	public SortedSet<T> remove(Iterable<T> elements) {
		return (SortedSet<T>) super.remove(elements);
	}
	
	@Override
	public SortedSet<T> add(T... elements) {
		return (SortedSet<T>) super.add(elements);
	}
	
	@Override
	public SortedSet<T> symmetricDifference(Iterable<T> elements) {
		return (SortedSet<T>) super.symmetricDifference(elements);
	}
	
	@Override
	public SortedSet<T> union(Iterable<T> elements) {
		return (SortedSet<T>) super.union(elements);
	}
	
	protected abstract SortedSet<T> createEmptySet();
	
	@Override
	public SortedSet<T> intersection(Iterable<T> elements) {
		return (SortedSet<T>) super.intersection(elements);
	}
}
