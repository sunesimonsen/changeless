package com.jayway.changeless.stacks;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class StackEqualsAndHashcodeComparedWithEmptyStackTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Stacks.of(42,41,41);
	}

	@Override
	protected Object createSecondInstance() {
		return Stacks.empty();
	}

}
