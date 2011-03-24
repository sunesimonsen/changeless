package com.jayway.collections;

import com.jayway.test.EqualsAndHashcodeTestSupport;

public class OptionalEqualsAndHashcode extends EqualsAndHashcodeTestSupport<Optional<String>>{

	@Override
	protected Optional<String> createFirstInstance() {
		return Optional.valueOf("first");
	}

	@Override
	protected Optional<String> createSecondInstance() {
		return Optional.valueOf("second");
	}
	
}
