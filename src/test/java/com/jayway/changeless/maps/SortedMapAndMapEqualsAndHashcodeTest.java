package com.jayway.changeless.maps;

import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;


public class SortedMapAndMapEqualsAndHashcodeTest extends EqualsAndHashcodeTestSupport {

	@Override
	protected Object createFirstInstance() {
		return SortedMaps.of(42,"42",41,"41",40,"40");
	}

	@Override
	protected Object createSecondInstance() {
		return Maps.of(40,"40",41,"41",42,"42", 43,"43");
	}

}
