package com.jayway.changeless.stubs;

import com.jayway.changeless.functions.Fn;

public class GetAgeFunction implements Fn<Person, Integer> {
	@Override
	public Integer apply(Person input) {
		return input.age();
	}
}
