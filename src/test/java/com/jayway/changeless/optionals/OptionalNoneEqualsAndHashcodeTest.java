package com.jayway.changeless.optionals;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

public class OptionalNoneEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport{

	@Override
	protected Object x() {
		return Optional.none();
	}

	@Override
	protected Object notX() {
		return Optional.valueOf("second");
	}
	
}
