package com.jayway.collections.immutable.vectors;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeComparedWithEmptyVectorTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Vectors.of(42,41,41);
	}

	@Override
	protected Object createSecondInstance() {
		return Vectors.of();
	}

}
