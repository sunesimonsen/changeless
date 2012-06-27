package com.jayway.changeless.queues;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class QueueEqualsAndHashcodeWithEmptyQueueTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Queues.of();
	}

	@Override
	protected Object notX() {
		return Queues.of(40,41,42);
	}

}
