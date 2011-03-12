package com.jayway.collections.immutable.tuples;

import org.junit.Test;

import com.jayway.collections.tuples.Tuples;

public class TupleTests {
	@Test(expected=IllegalArgumentException.class)
	public void throwsIfFirstIsNull() throws Exception {
		Tuples.of(null, "Foo");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsIfSecondIsNull() throws Exception {
		Tuples.of("Foo", null);
	}
}
