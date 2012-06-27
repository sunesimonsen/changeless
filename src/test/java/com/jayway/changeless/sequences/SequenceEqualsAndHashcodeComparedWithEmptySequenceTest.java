package com.jayway.changeless.sequences;

import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SequenceEqualsAndHashcodeComparedWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Sequences.of(42,41,41);
	}

	@Override
	protected Object notX() {
		return Sequences.of();
	}

}
