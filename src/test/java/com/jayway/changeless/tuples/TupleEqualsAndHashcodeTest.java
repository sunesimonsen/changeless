package com.jayway.changeless.tuples;

import com.jayway.changeless.tuples.Tuples;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
