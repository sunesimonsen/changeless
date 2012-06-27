package com.jayway.changeless.vectors;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.vectors.Vectors;


public class VectorEqualsAndHashcodeComparedWithEmptyVectorTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Vectors.of(42,41,41);
	}

	@Override
	protected Object notX() {
		return Vectors.of();
	}

}
