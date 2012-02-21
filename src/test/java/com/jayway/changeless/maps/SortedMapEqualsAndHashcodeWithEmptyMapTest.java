package com.jayway.changeless.maps;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SortedMapEqualsAndHashcodeWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return SortedMaps.<Integer, String>of();
	}

	@Override
	protected Object createSecondInstance() {
		return SortedMaps.of(40,"40",41,"41",42,"42");
	}

}
