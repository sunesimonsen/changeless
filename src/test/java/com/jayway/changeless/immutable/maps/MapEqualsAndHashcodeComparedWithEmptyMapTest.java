package com.jayway.changeless.immutable.maps;

import com.jayway.changeless.immutable.maps.Maps;
import com.jayway.test.EqualsAndHashcodeTestSupport;


public class MapEqualsAndHashcodeComparedWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Maps.of(42, "42", 41, "41", 40, "40");
	}

	@Override
	protected Object createSecondInstance() {
		return Maps.empty();
	}

}
