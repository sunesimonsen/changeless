package com.jayway.changeless.maps;

import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class MapEqualsAndHashcodeComparedWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Maps.of(42, "42", 41, "41", 40, "40");
	}

	@Override
	protected Object notX() {
		return Maps.empty();
	}

}
