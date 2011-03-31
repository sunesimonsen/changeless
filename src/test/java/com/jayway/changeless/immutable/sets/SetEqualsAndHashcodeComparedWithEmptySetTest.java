package com.jayway.changeless.immutable.sets;

import com.jayway.changeless.immutable.sets.Sets;
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
