package com.jayway.changeless.optionals;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;

public class NoneOptionalEqualsAndHashcode extends EqualsAndHashcodeTestSupport{

	@Override
	protected Object createFirstInstance() {
		return Optional.valueOf("first");	
	}

	@Override
	protected Object createSecondInstance() {
		return Optional.none();
	}
	
}
