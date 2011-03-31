package com.jayway.changeless.immutable.vectors;

import com.jayway.changeless.immutable.vectors.Vectors;
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
