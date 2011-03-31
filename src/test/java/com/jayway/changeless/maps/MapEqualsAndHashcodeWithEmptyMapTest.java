package com.jayway.changeless.maps;

import com.jayway.changeless.maps.Maps;
import com.jayway.changesless.test.EqualsAndHashcodeTestSupport;


public class MapEqualsAndHashcodeWithEmptyMapTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return Maps.of();
	}

	@Override
	protected Object createSecondInstance() {
		return Maps.of(40,"40",41,"41",42,"42");
	}

}
