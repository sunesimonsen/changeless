package com.jayway.changeless.sets;

import com.jayway.changeless.sets.Sets;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
