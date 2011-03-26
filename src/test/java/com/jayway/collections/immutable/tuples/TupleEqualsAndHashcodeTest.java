package com.jayway.collections.immutable.tuples;

import com.jayway.collections.tuples.Tuples;
import com.jayway.test.EqualsAndHashcodeTestSupport;


public class TupleEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Tuples.of(42, "42");
	}

	@Override
	protected Object createSecondInstance() {
		return Tuples.of(666, "666");
	}
}
