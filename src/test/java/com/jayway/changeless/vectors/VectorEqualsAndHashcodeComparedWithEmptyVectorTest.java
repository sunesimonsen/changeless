package com.jayway.changeless.vectors;

import com.jayway.changeless.vectors.Vectors;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
