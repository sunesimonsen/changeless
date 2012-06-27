package com.jayway.changeless.records;

import com.jayway.changeless.stubs.Person;
import com.jayway.changeless.test.EqualsAndHashcodeTestSupport;

public class RecordEqualsAndHashcodeTests extends EqualsAndHashcodeTestSupport {
	@Override
	protected Object x() {
		return Records.of(Person.class).name("Foo").age(21);
	}

	@Override
	protected Object notX() {
		return Records.of(Person.class).name("Foo").age(22);
	}
}


