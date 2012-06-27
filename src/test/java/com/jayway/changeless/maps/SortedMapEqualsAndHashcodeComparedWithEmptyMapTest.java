package com.jayway.changeless.maps;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SortedMapEqualsAndHashcodeComparedWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return SortedMaps.of(42, "42", 41, "41", 40, "40");
	}

	@Override
	protected Object notX() {
		return SortedMaps.<Integer, String>empty();
	}

}
