package com.jayway.changeless.predicates;

/**
 * An utility class for working with {@link Predicate} instances.
 */
public final class Predicates {
	private Predicates() { /* Static class */ }
	
	/**
	 * Returns a new predicate function that is the negation of the given predicate.
	 * @param <T> the type of the values that the predicate accepts.
	 * @param predicate the predicate to negate.
	 * @return a new predicate function that is the negation of the given predicate.
	 */
	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return new Predicate<T>() {
			@Override
			public Boolean apply(T input) {
				return !predicate.apply(input);
			}
		};
	}

	/**
	 * A predicate function that returns true for arguments that is not null;
	 * and false otherwise.
	 */
	public static Predicate<Object> notNull= new Predicate<Object>() {
			@Override
			public Boolean apply(Object input) {
				return input != null;
			}
		};
	
	/**
	 * Creates a predicate function that returns true for arguments that is 
	 * equals to the given element.
	 * @param element the element that arguments for the predicate should be compared with.
	 * @return an equals predicate function.
	 */
	public static Predicate<Object> equalsPredicate(final Object element) {
		return new Predicate<Object>() {
			@Override
			public Boolean apply(Object input) {
				return element.equals(input);
			}
		};
	}
}
