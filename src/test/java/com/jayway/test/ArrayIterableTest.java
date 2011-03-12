package com.jayway.test;

import com.jayway.collections.immutable.sequences.Sequences;

public class ArrayIterableTest extends IterableTestSupport<Integer> {

	@Override
	protected Iterable<Integer> createIterableWithOneOrMoreElements() {
		return Sequences.of(1,2,3,4,5) ;
	}

}
