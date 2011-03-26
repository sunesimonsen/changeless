package com.jayway.collections.immutable.sets;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeComparedWithEmptySetTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sets.of(42,41,41);
	}

	@Override
	protected Object createSecondInstance() {
		return Sets.of();
	}

}
