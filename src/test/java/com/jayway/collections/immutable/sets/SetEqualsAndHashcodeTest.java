package com.jayway.collections.immutable.sets;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sets.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Sets.of(40,41,42,43);
	}

}
