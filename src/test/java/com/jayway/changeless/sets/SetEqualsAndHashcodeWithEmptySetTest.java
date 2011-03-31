package com.jayway.changeless.sets;

import com.jayway.changeless.sets.Sets;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
