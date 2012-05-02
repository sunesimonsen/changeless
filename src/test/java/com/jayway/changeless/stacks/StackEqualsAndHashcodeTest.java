package com.jayway.changeless.stacks;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class StackEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Stacks.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Stacks.of(40,41,42);
	}

}
