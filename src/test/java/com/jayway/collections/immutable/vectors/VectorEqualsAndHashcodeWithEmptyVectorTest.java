package com.jayway.collections.immutable.vectors;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeWithEmptyVectorTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Vectors.of();
	}

	@Override
	protected Object createSecondInstance() {
		return Vectors.of(40,41,42);
	}

}
