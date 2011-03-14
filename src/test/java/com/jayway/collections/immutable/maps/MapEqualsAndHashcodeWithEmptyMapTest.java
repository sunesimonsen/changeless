package com.jayway.collections.immutable.maps;

import com.jayway.test.EqualsAndHashcodeTestSupport;


public class MapEqualsAndHashcodeWithEmptyMapTest extends EqualsAndHashcodeTestSupport<Map<Integer,String>> {

	@Override
	protected Map<Integer,String> createFirstInstance() {
		return Maps.of();
	}

	@Override
	protected Map<Integer,String> createSecondInstance() {
		return Maps.of(40,"40",41,"41",42,"42");
	}

}
