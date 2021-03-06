package com.jayway.changeless.vectors;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.vectors.Vectors;


public class VectorEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Vectors.of(42,41,40);
	}

	@Override
	protected Object notX() {
		return Vectors.of(40,41,42);
	}

}
