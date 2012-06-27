package com.jayway.changeless.optionals;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

public class NoneOptionalEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport{

	@Override
	protected Object x() {
		return Optional.valueOf("first");	
	}

	@Override
	protected Object notX() {
		return Optional.none();
	}
	
}
