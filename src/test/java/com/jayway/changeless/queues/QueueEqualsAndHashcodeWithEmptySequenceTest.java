package com.jayway.changeless.queues;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class QueueEqualsAndHashcodeWithEmptySequenceTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Queues.of();
	}

	@Override
	protected Object createSecondInstance() {
		return Queues.of(40,41,42);
	}

}
