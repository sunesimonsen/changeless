package com.jayway.collections;

import com.jayway.test.EqualsAndHashcodeTestSupport;

public class OptionalEqualsAndHashcode extends EqualsAndHashcodeTestSupport{

	@Override
	protected Object createFirstInstance() {
		return Optional.valueOf("first");
	}

	@Override
	protected Object createSecondInstance() {
		return Optional.valueOf("second");
	}
	
}
