package com.jayway.collections.immutable.vectors;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeWithEmptyVectorTest extends EqualsAndHashcodeTestSupport<Vector<Integer>> {

	@Override
	protected Vector<Integer> createFirstInstance() {
		return Vectors.of();
	}

	@Override
	protected Vector<Integer> createSecondInstance() {
		return Vectors.of(40,41,42);
	}

}
