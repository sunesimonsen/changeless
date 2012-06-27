package com.jayway.changeless.stacks;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class StackEqualsAndHashcodeWithEmptyStackTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Stacks.empty();
	}

	@Override
	protected Object notX() {
		return Stacks.of(40,41,42);
	}
}
