package com.jayway.changeless.predicates.integers;

import com.jayway.changeless.predicates.Predicate;

public class EvenPredicate implements Predicate<Integer> {
	@Override
	public boolean apply(Integer input) {
		return input % 2 == 0;
	}
}
