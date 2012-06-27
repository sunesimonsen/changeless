package com.jayway.changeless.vectors;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.vectors.Vectors;


public class VectorEqualsAndHashcodeWithEmptyVectorTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Vectors.of();
	}

	@Override
	protected Object notX() {
		return Vectors.of(40,41,42);
	}

}
