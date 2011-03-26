package com.jayway.collections.immutable.sequences;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SequenceEqualsAndHashcodeWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sequences.of();
	}

	@Override
	protected Object createSecondInstance() {
		return Sequences.of(40,41,42);
	}

}
