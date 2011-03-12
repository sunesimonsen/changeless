package com.jayway.collections.immutable.sequences;

import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;
import com.jayway.test.IterableTestSupport;


public class DefaultSequenceIteratorTest extends IterableTestSupport<Integer> {

	@Override
	protected Iterable<Integer> createIterableWithOneOrMoreElements() {
		Sequence<Integer> sequence = Sequences.empty();
		return sequence.addAll(1,2,3,4,5);
	}

}
