package com.jayway.changeless.queues;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class QueueEqualsAndHashcodeComparedWithEmptyQueueTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Queues.of(42,41,41);
	}

	@Override
	protected Object notX() {
		return Queues.of();
	}

}
