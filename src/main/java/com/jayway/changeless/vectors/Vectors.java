package com.jayway.changeless.vectors;

/**
 * A utility class for working with {@link Vector}'s.
 */
public final class Vectors {
	private Vectors() { /* Static class */ }
	
	/**
	 * Creates a new empty {@link Vector}.
	 * @param <T> the element type of the vector.
	 * @return an empty vector.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Vector<T> empty() {
		return MapVector.of();
	}

	/**
	 * Create a new {@link Vector} with the given elements.
	 * @param <T> the element type of the vector.
	 * @param elements the elements of the vector to be created.
	 * @return a new {@link Vector} with the given elements.
	 */
	public static <T> Vector<T> of(T... elements) {
		return MapVector.of(elements);
	}

	/**
	 * Create a new {@link Vector} with the given elements.
	 * @param <T> the element type of the vector.
	 * @param elements the elements of the vector to be created.
	 * @return a new {@link Vector} with the given elements.
	 */
	public static <T> Vector<T> copyOf(Iterable<T> elements) {
		return MapVector.copyOf(elements);
	}
}
