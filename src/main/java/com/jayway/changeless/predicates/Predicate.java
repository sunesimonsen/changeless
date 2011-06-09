package com.jayway.changeless.predicates;

import com.jayway.changeless.functions.Fn;


/**
 * A predicate function that returns true or false based on it's input parameter.
 * Predicates should not have side effects.
 * @param <T> the type of the input parameter.
 */
public interface Predicate<T> extends Fn<T, Boolean> {
	/**
	 * Applies this predicate to the given input.
	 * @param input the input to this predicate.
	 * @return true if this predicate applies to the given input; false otherwise.
	 */
	Boolean apply(T input);
}
