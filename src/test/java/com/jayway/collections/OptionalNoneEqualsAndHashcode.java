package com.jayway.collections;

import com.jayway.test.EqualsAndHashcodeTestSupport;

public class OptionalNoneEqualsAndHashcode extends EqualsAndHashcodeTestSupport{

	@Override
	protected Object createFirstInstance() {
		return Optional.none();
	}

	@Override
	protected Object createSecondInstance() {
		return Optional.valueOf("second");
	}
	
}
