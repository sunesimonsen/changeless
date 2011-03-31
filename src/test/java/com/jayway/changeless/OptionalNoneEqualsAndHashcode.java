package com.jayway.changeless;

import com.jayway.changeless.Optional;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;

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
