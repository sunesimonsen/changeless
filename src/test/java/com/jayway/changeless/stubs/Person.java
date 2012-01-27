package com.jayway.changeless.stubs;

import com.jayway.changeless.records.Record;

public interface Person extends Record<Person> {
	String name();
	Person name(String name);
	Address address();
	Person address(Address address);
	int age();
	Person age(int age);
}

