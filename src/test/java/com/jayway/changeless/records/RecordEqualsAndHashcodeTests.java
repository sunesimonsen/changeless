package com.jayway.changeless.records;

import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

public class RecordEqualsAndHashcodeTests extends EqualsAndHashcodeTestSupport {
	private interface Person extends Record {
		String name();
		Person name(String name);
		int age();
		Person age(int age);
	}

	@Override
	protected Object createFirstInstance() {
		return Records.of(Person.class).name("Foo").age(21);
	}

	@Override
	protected Object createSecondInstance() {
		return Records.of(Person.class).name("Foo").age(22);
	}
}


