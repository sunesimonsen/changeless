package com.jayway.changeless.maps;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SortedMapEqualsAndHashcodeWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return SortedMaps.<Integer, String>of();
	}

	@Override
	protected Object notX() {
		return SortedMaps.of(40,"40",41,"41",42,"42");
	}

}
