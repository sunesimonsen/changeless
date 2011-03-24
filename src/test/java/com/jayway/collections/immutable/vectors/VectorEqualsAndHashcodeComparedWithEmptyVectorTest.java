package com.jayway.collections.immutable.vectors;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class VectorEqualsAndHashcodeComparedWithEmptyVectorTest extends EqualsAndHashcodeTestSupport<Vector<Integer>> {

	@Override
	protected Vector<Integer> createFirstInstance() {
		return Vectors.of(42,41,41);
	}

	@Override
	protected Vector<Integer> createSecondInstance() {
		return Vectors.of();
	}

}
