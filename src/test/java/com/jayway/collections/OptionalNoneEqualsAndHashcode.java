package com.jayway.collections;

import com.jayway.test.EqualsAndHashcodeTestSupport;

public class OptionalNoneEqualsAndHashcode extends EqualsAndHashcodeTestSupport<Optional<String>>{

	@Override
	protected Optional<String> createFirstInstance() {
		return Optional.none();
	}

	@Override
	protected Optional<String> createSecondInstance() {
		return Optional.valueOf("second");
	}
	
}
