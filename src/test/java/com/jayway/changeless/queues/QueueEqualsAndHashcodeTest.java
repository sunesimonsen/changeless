package com.jayway.changeless.queues;

import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;
import com.jayway.changeless.vectors.Vectors;


public class QueueEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Queues.of(42,41,40);
	}

	@Override
	protected Object y() {
		return Sequences.of(42,41,40);
	}

	@Override
	protected Object z() {
		return Vectors.of(42,41,40);
	}

	@Override
	protected Object notX() {
		return Queues.of(40,41,42);
	}

}
