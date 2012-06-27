package com.jayway.changeless.sets;

import com.jayway.changeless.sets.Sets;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SetEqualsAndHashcodeComparedWithEmptySetTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Sets.of(42,41,41);
	}

	@Override
	protected Object notX() {
		return Sets.of();
	}

}
