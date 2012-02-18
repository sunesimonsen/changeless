package com.jayway.changeless.stubs;

import com.jayway.changeless.records.Record;

public interface Address extends Record<Address> {
	Address street(String street);
	String street();
	Address houseNumber(int houseNumber);
	int houseNumber();
}