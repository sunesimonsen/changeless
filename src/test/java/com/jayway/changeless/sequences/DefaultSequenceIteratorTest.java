package com.jayway.changeless.sequences;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.test.IterableTestSupport;


public class DefaultSequenceIteratorTest extends IterableTestSupport<Integer> {

	@Override
	protected Iterable<Integer> createIterableWithOneOrMoreElements() {
		Sequence<Integer> sequence = Sequences.empty();
		return sequence.add(1,2,3,4,5);
	}

}
