package com.jayway.changeless.sequences;

import com.jayway.changeless.sequences.Sequences;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


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
