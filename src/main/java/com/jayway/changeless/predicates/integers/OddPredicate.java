package com.jayway.changeless.predicates.integers;

import com.jayway.changeless.predicates.Predicate;

/**
 * A predicate function that returns true for integer arguments that is odd. 
 */
public class OddPredicate implements Predicate<Integer> {
	@Override
	public boolean matches(Integer input) {
		return input % 2 != 0;
	}
}