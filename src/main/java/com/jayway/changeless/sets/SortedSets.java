package com.jayway.changeless.sets;

/**
 * An utility class for working with sorted sets.
 */
public final class SortedSets {
	private SortedSets() { /* Static class */ }
	
	/**
	 * Create an empty sorted set.
	 * @param <T> the type of the elements in the set.
	 * @return an empty set.
	 */
	public static <T extends Comparable<T>> SortedSet<T> empty() {
		return ImmutableTreeSet.empty();
	}
	
	/**
	 * Creates a sorted set containing the given elements.
	 * @param <T> the type of the elements in the set.
	 * @param elements the elements of the sequence to create.
	 * @return a set containing the given elements.
	 */
	public static <T extends Comparable<T>> SortedSet<T> copyOf(Iterable<T> elements) {
		SortedSet<T> result = SortedSets.empty();
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}

	/**
	 * Creates a sorted set containing the given elements.
	 * @param <T> the type of the elements in the set.
	 * @param elements the elements of the sequence to create.
	 * @return a set containing the given elements.
	 */
	public static <T extends Comparable<T>> SortedSet<T> of(T... elements) {
		SortedSet<T> result = SortedSets.empty();
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}
}
