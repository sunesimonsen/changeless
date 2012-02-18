package com.jayway.changeless.stubs;

import com.jayway.changeless.functions.Fn;

public class GetNameFunction implements Fn<Person, String> {
	@Override
	public String apply(Person input) {
		return input.name();
	}
}
