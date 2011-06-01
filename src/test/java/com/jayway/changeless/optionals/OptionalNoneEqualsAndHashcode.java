package com.jayway.changeless.optionals;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

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
