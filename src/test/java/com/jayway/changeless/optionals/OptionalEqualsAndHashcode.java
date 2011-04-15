package com.jayway.changeless.optionals;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

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
