package com.jayway.collections.immutable.tuples;

import com.jayway.collections.tuples.Tuple;
import com.jayway.collections.tuples.Tuples;
import com.jayway.test.EqualsAndHashcodeTestSupport;


public class TupleEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport<Tuple<Integer, String>> {

	@Override
	protected Tuple<Integer, String> createFirstInstance() {
		return Tuples.of(42, "42");
	}

	@Override
	protected Tuple<Integer, String> createSecondInstance() {
		return Tuples.of(666, "666");
	}
}
