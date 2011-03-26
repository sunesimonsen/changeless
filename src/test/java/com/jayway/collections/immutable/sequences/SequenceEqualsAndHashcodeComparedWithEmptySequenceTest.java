package com.jayway.collections.immutable.sequences;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class SequenceEqualsAndHashcodeComparedWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Sequences.of(42,41,41);
	}

	@Override
	protected Object createSecondInstance() {
		return Sequences.of();
	}

}
