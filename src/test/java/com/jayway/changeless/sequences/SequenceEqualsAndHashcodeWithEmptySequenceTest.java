package com.jayway.changeless.sequences;

import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SequenceEqualsAndHashcodeWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Sequences.of();
	}

	@Override
	protected Object notX() {
		return Sequences.of(40,41,42);
	}

}
