package com.jayway.changeless.predicates;



/**
 * A predicate function that returns true or false based on it's input parameter.
 * Predicates should not have side effects.
 * @param <T> the type of the input parameter.
 */
public interface Predicate<T> {
	/**
	 * Applies this predicate to the given input.
	 * @param input the input to this predicate.
	 * @return true if this predicate applies to the given input; false otherwise.
	 */
	boolean matches(T input);
}
