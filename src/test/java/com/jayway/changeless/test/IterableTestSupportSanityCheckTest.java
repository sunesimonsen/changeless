package com.jayway.changeless.test;

import java.util.ArrayList;
import java.util.Arrays;

public class IterableTestSupportSanityCheckTest extends IterableTestSupport<Integer>{

	@Override
	protected Iterable<Integer> createIterableWithOneOrMoreElements() {
		return new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
	}

}
