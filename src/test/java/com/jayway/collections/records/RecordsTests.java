package com.jayway.collections.records;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;


public class RecordsTests {
	@Test
	public void createRecord() throws Exception {
		Person p1 = Records.of(Person.class);
		
		Address a1 = Records.of(Address.class);
		a1 = a1.street("foo").houseNumber(15);
		
		p1 = p1.address(a1);
		assertEquals("foo", p1.address().street());
		assertEquals(15, p1.address().houseNumber());
		
		Person p2 = Records.of(Person.class);
		
		Address a2 = Records.of(Address.class);
		a2 = a2.street("foo").houseNumber(15);
		
		p2 = p2.address(a2);
		
		assertEquals(p1.hashCode(), p2.hashCode());
		assertEquals(p1.toString(), p2.toString());
		assertEquals(p1, p2);
		
		Person p3 = Records.of(Person.class);
		p3 = p3.address(a2.street("bar"));
		assertFalse("not equals", p1.equals(p3));
	}
}

interface Person extends Record {
	Person name(String name);
	String name();
	Address address();
	Person address(Address address);
}

interface Address extends Record {
	Address street(String street);
	String street();
	Address houseNumber(int houseNumber);
	int houseNumber();
}
