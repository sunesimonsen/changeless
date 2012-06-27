package com.jayway.changeless.queues;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class QueueEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Queues.of(42,41,40);
	}

	@Override
	protected Object createSecondInstance() {
		return Queues.of(40,41,42);
	}

}
