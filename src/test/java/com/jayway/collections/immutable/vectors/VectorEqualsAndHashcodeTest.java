package com.jayway.collections.immutable.vectors;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Vectors.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Vectors.of(40,41,42);
	}

}
