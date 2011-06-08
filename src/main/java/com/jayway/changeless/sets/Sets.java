package com.jayway.changeless.sets;

/**
 * An utility class for working with sets.
 */
public final class Sets {
	private Sets() { /* Static class */ }
	
	/**
	 * Create an empty set.
	 * @param <T> the type of the elements in the set.
	 * @return an empty set.
	 */
	public static <T> Set<T> empty() {
		return ImmutableHashSet.empty();
	}
	
	/**
	 * Creates a set containing the given elements.
	 * @param <T> the type of the elements in the set.
	 * @param elements the elements of the sequence to create.
	 * @return a set containing the given elements.
	 */
	public static <T> Set<T> copyOf(Iterable<T> elements) {
		return ImmutableHashSet.copyOf(elements);
	}

	/**
	 * Creates a set containing the given elements.
	 * @param <T> the type of the elements in the set.
	 * @param elements the elements of the sequence to create.
	 * @return a set containing the given elements.
	 */
	public static <T> Set<T> of(T... elements) {
		return ImmutableHashSet.of(elements);
	}
}
