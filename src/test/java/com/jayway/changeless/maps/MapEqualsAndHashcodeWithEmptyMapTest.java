package com.jayway.changeless.maps;

import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class MapEqualsAndHashcodeWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object x() {
		return Maps.of();
	}

	@Override
	protected Object notX() {
		return Maps.of(40,"40",41,"41",42,"42");
	}

}
