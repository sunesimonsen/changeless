package com.jayway.changeless.immutable.sequences;

import com.jayway.changeless.immutable.sequences.Sequence;
import com.jayway.changeless.immutable.sequences.Sequences;
import com.jayway.test.IterableTestSupport;


public class DefaultSequenceIteratorTest extends IterableTestSupport<Integer> {

	@Override
	protected Iterable<Integer> createIterableWithOneOrMoreElements() {
		Sequence<Integer> sequence = Sequences.empty();
		return sequence.add(1,2,3,4,5);
	}

}
