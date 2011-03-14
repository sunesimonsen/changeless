package com.jayway.collections.immutable.sets;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeWithEmptySetTest extends EqualsAndHashcodeTestSupport<Set<Integer>> {

	@Override
	protected Set<Integer> createFirstInstance() {
		return Sets.of();
	}

	@Override
	protected Set<Integer> createSecondInstance() {
		return Sets.of(40,41,42);
	}

}
