package com.jayway.collections.immutable.sets;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeWithEmptySetTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sets.of();
	}

	@Override
	protected Object createSecondInstance() {
		return Sets.of(40,41,42);
	}

}
