package com.jayway.collections;

import com.jayway.test.EqualsAndHashcodeTestSupport;

public class NoneOptionalEqualsAndHashcode extends EqualsAndHashcodeTestSupport<Optional<String>>{

	@Override
	protected Optional<String> createFirstInstance() {
		return Optional.valueOf("first");	
	}

	@Override
	protected Optional<String> createSecondInstance() {
		return Optional.none();
	}
	
}
