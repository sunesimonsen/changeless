package com.jayway.changeless.maps;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SortedMapEqualsAndHashcodeComparedWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return SortedMaps.of(42, "42", 41, "41", 40, "40");
	}

	@Override
	protected Object createSecondInstance() {
		return SortedMaps.<Integer, String>empty();
	}

}
