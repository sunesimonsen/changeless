package com.jayway.changeless.queues;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class QueueEqualsAndHashcodeComparedWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Queues.of(42,41,41);
	}

	@Override
	protected Object createSecondInstance() {
		return Queues.of();
	}

}
