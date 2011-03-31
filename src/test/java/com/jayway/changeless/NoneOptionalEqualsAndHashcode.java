package com.jayway.changeless;

import com.jayway.changeless.Optional;
import com.jayway.test.EqualsAndHashcodeTestSupport;

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
