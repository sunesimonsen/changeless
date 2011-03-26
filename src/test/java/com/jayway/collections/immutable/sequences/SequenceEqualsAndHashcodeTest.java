package com.jayway.collections.immutable.sequences;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SequenceEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sequences.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Sequences.of(40,41,42);
	}

}
