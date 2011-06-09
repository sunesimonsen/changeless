package com.jayway.changeless.predicates.integers;

import com.jayway.changeless.predicates.Predicate;

/**
 * A predicate evaluating to true for even integers.
 */
public class EvenPredicate implements Predicate<Integer> {
	@Override
	public Boolean apply(Integer input) {
		return input % 2 == 0;
	}
}
