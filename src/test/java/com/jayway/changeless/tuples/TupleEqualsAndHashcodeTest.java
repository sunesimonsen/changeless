package com.jayway.changeless.tuples;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.tuples.Tuples;


public class TupleEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Tuples.of(42, "42");
	}

	@Override
	protected Object notX() {
		return Tuples.of(666, "666");
	}
}
