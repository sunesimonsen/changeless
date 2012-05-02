package com.jayway.changeless.stacks;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class StackEqualsAndHashcodeWithEmptyStackTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Stacks.empty();
	}

	@Override
	protected Object createSecondInstance() {
		return Stacks.of(40,41,42);
	}
}
