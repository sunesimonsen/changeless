package com.jayway.collections.immutable.sets;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeComparedWithEmptySetTest extends EqualsAndHashcodeTestSupport<Set<Integer>> {

	@Override
	protected Set<Integer> createFirstInstance() {
		return Sets.of(42,41,41);
	}

	@Override
	protected Set<Integer> createSecondInstance() {
		return Sets.of();
	}

}
