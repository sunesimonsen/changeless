package com.jayway.changeless.immutable.tuples;

import org.junit.Test;

import com.jayway.changeless.tuples.Tuples;

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
